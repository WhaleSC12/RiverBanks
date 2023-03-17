import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

public class DataWriter {
    private static final String USERS_JSON = "json/dat/users.json"; // to point refs later more easily
    private static final String USER_COURSE_DATA_JSON = "json/dat/userCourseData.json";
    private static final String COURSES_JSON = "json/dat/courses.json";

    /**
     * Writes into the given file at a certain point. Created for use with JSON files structured around Hashmaps,
     * does not support adding arrays
     * Use .atKey() to traverse down from the root until you reach the key whose value you wish to replace,
     * then use .write() to write in the data you want at that value
     */
    private static class JSONWriter {
        private final String filePath;
        private final JSONObject root;
        private final Stack<String> keyStack = new Stack<>();

        /**
         * @param filePath JSON file which contains the object being written into
         * @throws IOException    if the given file does not exist, IOException is thrown
         * @throws ParseException if the given JSON cannot be parsed, ParseException is thrown
         */
        protected JSONWriter(String filePath) throws IOException, ParseException {
            this.filePath = filePath;
            this.root = fetchRoot(filePath);
        }

        /**
         * Fetches the JSON object from the given file
         *
         * @param targetFile file which contains the json object
         * @return the JSON object from the file
         */
        private JSONObject fetchRoot(String targetFile) throws IOException, ParseException {
            FileReader fileReader = new FileReader(targetFile);
            JSONObject data = (JSONObject) new JSONParser().parse(fileReader);
            fileReader.close();
            return data;
        }

        /**
         * Use to traverse downwards from the root in the JSON file this JSONWriter was created at
         * Can be chained
         *
         * @param key key of the value being traversed into
         * @return returns an instance of self for the purpose of method chaining
         */
        protected JSONWriter atKey(String key) {
            keyStack.add(key);
            return this;
        }

        /**
         * @return the current value the JSONWriter is at from .at()
         * if no traversal has been initiated, will return root
         */
        protected JSONObject getCurrentJSONObject() {
            JSONObject place = root;
            for (String forwardKey : keyStack) place = (JSONObject) place.get(forwardKey);
            return place;
        }

        /**
         * Write the data at newData into the JSON file used to create this writer.
         * This empties the key stack and sets the current object to the root, returning the writer to its default.
         *
         * @param newData data to be written into the object at the current position
         * @return -1 on failure (IOException), else 0
         */
        protected int write(JSONObject newData) {
            JSONObject currentJSONObject = newData;
            // logarithmic complexity go BRRRRRR
            while (!keyStack.isEmpty()) {
                JSONObject place = root;
                String backwardKey = keyStack.pop();
                for (String forwardKey : keyStack) place = (JSONObject) place.get(forwardKey);
                place.put(backwardKey, currentJSONObject);
                currentJSONObject = place;
            }
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                currentJSONObject.writeJSONString(fileWriter);
            } catch (IOException e) {
                return -1;
            }

            return 0;
        }
    }

    /**
     * Save the user's data (not course progress and grades) into the User JSON
     *
     * @param user user whose data should be saved
     * @return -1 on failure, 0 otherwise
     */
    public static int writeUserData(User user) {
        // putting all references to RegisteredUser user at top to improve readability
        UUID userUUID = user.getUUID();
        JSONObject tempData = new JSONObject();
        tempData.put("firstName", user.getFirstName());
        tempData.put("lastName", user.getLastName());
        tempData.put("email", user.getEmail());
        tempData.put("phoneNumber", user.getPhoneNumber());
        tempData.put("clearance", user.getClearance());
        tempData.put("username", user.getUsername());
        tempData.put("password", user.getPassword());


        try {
            return new JSONWriter(USERS_JSON)
                    .atKey(userUUID.toString())
                    .write(tempData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userUUID user whose course progress is being written
     * @param course   course whose data is being saved
     * @return -1 on failure, else 0
     */
    public static int writeUserCourseData(UUID userUUID, Course course) {
        JSONObject courseData = new JSONObject();
        courseData.put("lessonsCompleted", course.getUserProgress());
        courseData.put("lessonGrades", course.fetchGrades());
        courseData.put("finalGrade", course.getFinalGrade());

        try {
            return new JSONWriter(USER_COURSE_DATA_JSON)
                    .atKey(userUUID.toString())
                    .atKey(course.getUUID().toString())
                    .write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }

    /**
     * Store's course information, such as lessons, content, tests, etc. into the courses json
     *
     * @param course course whose data is being stored
     * @return -1 on failure, else 0
     */
    public static int writeCourseData(Course course) {
        JSONObject courseData = new JSONObject();
        courseData.put("courseTitle", course.getTitle());
        courseData.put("description", course.getDescription());
        courseData.put("language", course.getLanguage());
        courseData.put("authorUUID", course.getAuthor().getUUID());
        JSONArray lessonList = new JSONArray();
        for (Lesson l : course.getLessons()) {
            Test test = l.getTest();
            JSONArray testData = new JSONArray();
            for (Question q :
                    test.getQuestions()) {
                JSONObject questionData = new JSONObject();
                questionData.put("prompt", q.getPrompt());
                JSONArray answersArr = new JSONArray();
                for (var v : q.getAnswerList()) {
                    JSONObject answerData = new JSONObject();
                    answerData.put(v.getKey(), v.getValue() ? 1 : 0);
                    answersArr.add(answerData);
                }
                questionData.put("answers", answersArr);
                testData.add(questionData);
            }
            Map<String, Object> tMap = Map.of(
                    "title", l.getTitle(),
                    "content", l.getContent(),
                    "test", testData
            );
            lessonList.add(new HashMap<>(tMap));
        }
        courseData.put("lessons", lessonList);

        try {
            return new JSONWriter(COURSES_JSON)
                    .atKey(course.getUUID().toString())
                    .write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }
}
