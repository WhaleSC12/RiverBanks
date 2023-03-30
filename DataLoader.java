import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Class for loading data into program memory from JSONs
 * Functions by pulling the entire file into memory as a large Map, then
 * using helper functions to parse it and return usable data.
 */
public class DataLoader {

    /**
     * Pulls the file root (toplevel of the JSONObject) from the file
     *
     * @param file relative file path to the file to be pulled
     * @return returns the JSONObject representing the root, else null
     */
    private static JSONObject fetchRoot(String file) {
        try (FileReader fileReader = new FileReader(file)) {
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper for getUsers which fetches the user file and casts it to a useful datatype
     *
     * @return hashmap representation of the jsonobject in user file
     */
    private static HashMap<String, HashMap<String, String>> getUserData() {
        JSONObject root = fetchRoot("json/dat/users.json");
        return (HashMap<String, HashMap<String, String>>) new HashMap(root);
    }

    /**
     * loads an arraylist containing all users and returns it
     *
     * @return an arraylist containing all users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        for (var entry : getUserData().entrySet()) {
            var val = entry.getValue();
            User user = new User(UUID.fromString(entry.getKey()), val.get("username"), val.get("password"), val.get("firstName"), val.get("lastName"), val.get("email"), val.get("phoneNumber"), val.get("clearance"));
            userList.add(user);
        }
        return userList;
    }

    /**
     * Helper method used to recursively get all comments
     *
     * @param commentArray json comment array filled with all the necessary data
     * @return an arraylist<comment> filled with all comment data to any depth
     */
    private static ArrayList<Comment> getCommentList(JSONArray commentArray) {
        ArrayList<Comment> commentList = new ArrayList<>();
        for (var v : commentArray) {
            JSONObject jsonComment = (JSONObject) v;
            Comment comment = new Comment(UUID.fromString((String) jsonComment.get("userUUID")), (String) jsonComment.get("content"));
            commentList.add(comment);
            ArrayList<Comment> replyList = comment.getCommentList();
            JSONArray comArr = (JSONArray) jsonComment.get("comments");
            replyList.addAll(getCommentList(comArr));
        }
        return commentList;
    }

    /**
     * Creates an arraylist of modules from the passed JSONArray containing module data
     *
     * @param moduleArray JSONArray containing module data
     * @return Arraylist of modules created from ModuleArray
     */
    private static ArrayList<Module> getModuleArray(JSONArray moduleArray) {
        ArrayList<Module> moduleList = new ArrayList<>();
        for (var v : moduleArray) {
            JSONObject jsonModule = (JSONObject) v;
            Module module = new Module((String) jsonModule.get("title"), (String) jsonModule.get("description"), (String) jsonModule.get("content"));
            moduleList.add(module);
        }
        return moduleList;
    }

    /**
     * Creates a test object from its jsonobject
     *
     * @param testObject JSONObject containing the test data, obtained by calling .get("test") on a lesson object
     * @return a Test form the JSONObject testObject
     */
    private static Test getTest(JSONObject testObject) {
        Test test = new Test((String) testObject.get("title"), (String) testObject.get("description"));
        ArrayList<Question> questionList = new ArrayList<>();
        JSONArray questionArray = (JSONArray) testObject.get("questions");
        for (var v : questionArray) {
            JSONObject jsonQuestion = (JSONObject) v;
            Question question = new Question();
            question.setPrompt((String) jsonQuestion.get("prompt"));
            ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();
            JSONArray jsonAnswerList = (JSONArray) jsonQuestion.get("answers");
            for (var z : jsonAnswerList) {
                JSONObject jsonAnswer = (JSONObject) z;
                AbstractMap.SimpleEntry<String, Boolean> answer = new AbstractMap.SimpleEntry<>((String) jsonAnswer.get("text"), (boolean) jsonAnswer.get("correct"));
                answerList.add(answer);
            }
            question.setAnswerList(answerList);
            questionList.add(question);
        }
        test.setQuestionList(questionList);
        return test;
    }

    /**
     * Creates an arraylist of lessons from a JSONArray, typically acquired by calling .get("lessons") on the course object
     *
     * @param lessonArray JSONArray which contains the lesson data
     * @return an arraylist of lessons generated from the data
     */
    private static ArrayList<Lesson> getLessonList(JSONArray lessonArray) {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        for (var v : lessonArray) {
            JSONObject jsonLesson = (JSONObject) v;
            JSONObject jsonTest = (JSONObject) jsonLesson.get("test");
            Test test = getTest(jsonTest);
            Lesson lesson = new Lesson((String) jsonLesson.get("title"), (String) jsonLesson.get("description"), test);
            ArrayList<Comment> commentList = lesson.getCommentList();
            JSONArray jsonCommentArray = (JSONArray) jsonLesson.get("comments");
            commentList.addAll(getCommentList(jsonCommentArray));
            ArrayList<Module> moduleList = lesson.getModuleList();
            JSONArray modulesObject = (JSONArray) jsonLesson.get("modules");
            moduleList.addAll(getModuleArray(modulesObject));
            lessonList.add(lesson);
        }
        return lessonList;
    }

    /**
     * returns an arraylist containing all courses in the course file and returns it
     *
     * @return arraylist of courses
     */
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        JSONObject courseData = fetchRoot("json/dat/courses.json");
        for (Object objectKey : courseData.keySet()) {
            String key = (String) objectKey;
            HashMap<String, Object> value = (HashMap<String, Object>) courseData.get(objectKey);
            Course course = new Course(UUID.fromString(key), (String) value.get("title"), (String) value.get("description"), UUID.fromString((String) value.get("authorUUID")), Language.valueOf((String) value.get("language")));
            JSONArray jsonLessonList = (JSONArray) value.get("lessons");
            ArrayList<Lesson> lessonList = course.getLessonList();
            lessonList.addAll(getLessonList(jsonLessonList));
            courseList.add(course);
        }
        return courseList;
    }


    /**
     * returns a map of usercoursedata sorted by useruuid and course uuid
     *
     * @return a hashmap<useruuid, hashmap<courseuuid, usercoursedata>> containing all data in file
     */
    public static HashMap<UUID, HashMap<UUID, UserCourse>> getUserCourses() {
        HashMap<UUID, HashMap<UUID, UserCourse>> out = new HashMap<>();
        JSONObject root = fetchRoot("json/dat/userCourseData.json");
        for (Object key : root.keySet()) {
            UUID userUUID = UUID.fromString((String) key);
            HashMap<String, HashMap<String, Object>> value = (HashMap<String, HashMap<String, Object>>) root.get(key);
            HashMap<UUID, UserCourse> dat = new HashMap<>();
            for (var v : value.entrySet()) {
                UUID courseUUID = UUID.fromString(v.getKey());
                HashMap<String, Object> val = v.getValue();
                JSONArray arr = (JSONArray) val.get("lessonGrades");
                ArrayList<Double> gradeList = new ArrayList<>(arr);
                UserCourse userCourse = new UserCourse(userUUID, courseUUID, Math.toIntExact((long) val.get("lessonsCompleted")), gradeList);
                dat.put(UUID.fromString(v.getKey()), userCourse);
            }
            out.put(userUUID, dat);
        }
        return out;
    }
}
