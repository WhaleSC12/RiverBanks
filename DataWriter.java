import org.json.simple.JSONArray;
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

public class DataWriter {
    private static final String USERS_JSON = "json/dat/users.json"; // to point refs later more easily
    private static final String USER_COURSE_DATA_JSON = "json/dat/userCourseData.json";
    private static final String COURSES_JSON = "json/dat/courses.json";

    private static class JSONWriter {
        private String filePath;
        private JSONObject allData;
        private JSONObject currentJSONObject;
        private ArrayList<UUID> keyStack = new ArrayList<>();

        protected JSONWriter(String filePath) {
            this.filePath = filePath;
        }

        protected JSONWriter obj(UUID key) {
            currentJSONObject = (JSONObject) currentJSONObject.get(key);
            keyStack.add(key);
            return this;
        }

        protected void write() {

        }
    }

    /**
     * Writes data into a JSON file at the given key as a value
     *
     * @param targetFile   the JSON within which to write
     * @param dataToWrite  the JSONObject which should be written into the file at the key
     * @param keyToWriteAt the key within the target file in which the data should be written
     * @return -1 upon exception or failure, otherwise 0
     * @author Raymond Konarski
     */
    private static int writeDataAtKey(String targetFile, JSONObject dataToWrite, UUID keyToWriteAt) {
        // yes, i realize i'm reading the whole file into memory, but we don't currently have a singleton for all data
        // catching for the sake of doing something with the code later, but otherwise this doesn't do anything but slow everything down
        JSONObject allData;
        try (FileReader fileReader = new FileReader(targetFile)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException | ParseException e) {
            return -1;
        }
        allData.put(keyToWriteAt.toString(), dataToWrite);
        try (FileWriter fileWriter = new FileWriter(targetFile)) {
            allData.writeJSONString(fileWriter);
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }

    /**
     * Writes data into a JSON file at the given key within another JSON object at the keyToWriteWithin as a value
     *
     * @param targetFile       the JSON within which to write
     * @param dataToWrite      the JSONObject which should be written into the file at the key
     * @param keyToWriteAt     the key within the target file in which the data should be written
     * @param keyToWriteWithin the key within which the object which the keyToWriteAt points to reside
     * @return -1 upon exception or failure, otherwise 0
     * @author Raymond Konarski
     */
    private static int writeDataWithinKeyAtKey(String targetFile, JSONObject dataToWrite, UUID keyToWriteAt, UUID keyToWriteWithin) {
        JSONObject allData;
        try (FileReader fileReader = new FileReader(targetFile)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException | ParseException e) {
            return -1;
        }
        JSONObject targetToWriteWithin = (JSONObject) allData.get(keyToWriteWithin);
        targetToWriteWithin.put(keyToWriteAt, dataToWrite);
        allData.put(keyToWriteWithin, targetToWriteWithin);
        try (FileWriter fileWriter = new FileWriter(targetFile)) {
            allData.writeJSONString(fileWriter);
        } catch (IOException e) {
            return -1;
        }
        return 0;
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

        return writeDataAtKey(USERS_JSON, tempData, userUUID);
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userUUID user whose course progress is being written
     * @param course   course whose data is being saved
     * @return -1 on failure, else 0
     */
    public static int writeUserCourseData(UUID userUUID, Course course) {
        JSONObject userCourses = new JSONObject();
        JSONObject courseData = new JSONObject();
        courseData.put("lessonsCompleted", course.getUserProgress());
        courseData.put("lessonGrades", course.fetchGrades());
        courseData.put("finalGrade", course.getFinalGrade());

        return writeDataWithinKeyAtKey(USER_COURSE_DATA_JSON, courseData, course.getUUID(), userUUID);
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
        courseData.put("language", course.getLanguage());
        courseData.put("authorUUID", course.getAuthor().getUUID());
        JSONArray lessonList = new JSONArray();
        for (Lesson l : course.getLessons()) {
            Map<String, Object> tMap = Map.of(
                    "title", l.getTitle(),
                    "content", l.getContent(),
                    "test", l.getTest() // TODO: don't write an objet to JSON, unless the lib supports that, still haven't tried
            );
            lessonList.add(new HashMap<>(tMap));
        }
        courseData.put("lessons", lessonList);

        return writeDataAtKey(COURSES_JSON, courseData, course.getUUID());
    }
}
