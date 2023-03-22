import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Used to write user and course data into their respective JSONs.
 * This is achieved by pulling the entire JSON file into memory,
 * replacing the part we want saved with new data,
 * then writing back to the JSON.
 */
public class DataWriter {
    private static final String USERS_JSON = "json/dat/users.json"; // to point refs later more easily, ideally we use a singleton or something
    private static final String USER_COURSE_DATA_JSON = "json/dat/userCourseData.json";
    private static final String COURSES_JSON = "json/dat/courses.json";

    /**
     * Writes into the given file at a certain point. Created for use with JSON files structured around Hashmaps,
     * does not support adding arrays
     * Use .atKey() to traverse down from the root until you reach the key whose value you wish to replace,
     * then use .write() to write in the data you want at that value
     */
    public static class JSONWriter implements AutoCloseable {
        private final String filePath;
        private final JSONObject root;
        private JSONObject currentPlace;
        private final ArrayList<String> keys = new ArrayList<>();
        private FileWriter fileWriter;

        /**
         * @param filePath JSON file which contains the object being written into
         * @throws IOException    if the given file does not exist, IOException is thrown
         * @throws ParseException if the given JSON cannot be parsed, ParseException is thrown
         */
        public JSONWriter(String filePath) throws IOException, ParseException {
            this.filePath = filePath;
            this.root = fetchRoot(filePath);
            this.currentPlace = root;
            try {
                fileWriter = new FileWriter(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e); // TODO: exception bad
            }
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
            keys.add(key);
            return this;
        }

        /**
         * Write the data at newData into the JSON file used to create this writer.
         * To write with this, an Object must be JSONAware. This can either be done by manually filling a new JSONObject
         * or JSONArray with values, or by implementing the JSONAware interface.
         *
         * @param newData JSONAware object to be written into the file opened on instantiation of this writer
         * @return -1 on failure (IOException), else 0
         */
        protected int write(JSONAware newData) {
            for (int i = 0; i < keys.size() - 1; i++) {
                currentPlace.get(keys.get(i));
            }
            currentPlace.put(keys.get(keys.size() - 1), newData);
            try {
                root.writeJSONString(fileWriter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return 0;
        }

        /**
         * Write the String at newData into the JSON file used to create this writer at getCurrentJSONObject()
         * Take note that the string in newData will be surrounded by quotes.
         *
         * @param newData JSONAware object to be written into the file opened on instantiation of this writer
         * @return -1 on failure (IOException), else 0
         */
        protected int write(String newData) { // TODO: create a helper class to discriminate between strings to be "" and JSONStrings
            for (int i = 0; i < keys.size() - 1; i++) {
                currentPlace.get(keys.get(i));
            }
            currentPlace.put(keys.get(keys.size() - 1), newData);
            try {
                root.writeJSONString(fileWriter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return 0;
        }

        @Override
        public void close() throws IOException {
            fileWriter.close();
        }
    }

    /**
     * Save the user's data (not course progress and grades) into the User JSON
     *
     * @param user user whose data should be saved
     * @return -1 on failure, 0 otherwise
     */
    public static int writeUserData(User user) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            return jsonWriter.atKey(user.getUUID().toString()).write(user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
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

        try(JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            return jsonWriter.atKey(userUUID.toString()).atKey(course.getUUID().toString()).write(courseData);
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
        courseData.put("authorUUID", course.getAuthorUUID().toString());
        JSONArray lessonList = new JSONArray();
        for (Lesson l : course.getLessons()) {
            Test test = l.getTest();
            JSONArray testData = new JSONArray();
            for (Question q : test.getQuestions()) {
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
            Map<String, Object> tMap = Map.of("title", l.getTitle(), "content", l.getContent(), "test", testData);
            lessonList.add(new HashMap<>(tMap));
        }
        courseData.put("lessons", lessonList);

        try(JSONWriter jsonWriter = new JSONWriter(COURSES_JSON)) {
            return jsonWriter.atKey(course.getUUID().toString()).write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }
}
