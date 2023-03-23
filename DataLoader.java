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
        } catch (IOException | ParseException ignored) {
            // TODO: someone handle the exception in a better way
        }
        return null;
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
            ArrayList<Course.Lesson> lessonList = new ArrayList<>();
            JSONArray jsonLessonList = (JSONArray) value.get("lessons");
            for (var l : jsonLessonList) {
                HashMap<String, Object> entry = (HashMap<String, Object>) l;
                Course.Lesson.Test test = new Course.Lesson.Test();
                ArrayList<Course.Lesson.Test.Question> questionList = new ArrayList<>();
                JSONArray jsonQuestionArray = (JSONArray) entry.get("test");
                for (var obj : jsonQuestionArray) {
                    JSONObject jsonQuestion = (JSONObject) obj;
                    Course.Lesson.Test.Question question = new Course.Lesson.Test.Question();
                    question.setPrompt((String) jsonQuestion.get("prompt"));
                    JSONArray jsonQuestionAnswerList = (JSONArray) jsonQuestion.get("answers");
                    ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();
                    for (var objAnswer : jsonQuestionAnswerList) {
                        JSONObject answer = (JSONObject) objAnswer;
                        AbstractMap.SimpleEntry<String, Boolean> answerData = new AbstractMap.SimpleEntry<>((String) answer.get("text"), (boolean) answer.get("correct"));
                        answerList.add(answerData);
                    }
                    question.setAnswerList(answerList);
                    questionList.add(question);
                }
                test.setQuestionList(questionList);
                Course.Lesson lesson = new Course.Lesson((String) entry.get("title"), (String) entry.get("description"), (String) entry.get("content"), test);
                course.addLesson(lesson);
            }
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
