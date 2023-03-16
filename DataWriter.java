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
     * Writes the user into users.json to be saved
     *
     * @param user the user to be written into storage
     * @return -1 on failure, otherwise 0
     * @author Raymond
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

    public static int writeUserCourseData(UUID userUUID, Course course) {
        JSONObject userCourses = new JSONObject();
        JSONObject courseData = new JSONObject();
        courseData.put("lessonsCompleted", course.getUserProgress());
        courseData.put("lessonGrades", course.fetchGrades());
        courseData.put("finalGrade", course.getFinalGrade());

        return writeDataWithinKeyAtKey(USER_COURSE_DATA_JSON, courseData, course.getUUID(), userUUID);
    }

    public static int writeCourseData(Course course) {
        String courseUUID = course.getUUID().toString();
        HashMap<String, Object> courseData = new HashMap<>();
        courseData.put("courseTitle", course.getTitle());
        courseData.put("language", course.getLanguage());
        courseData.put("authorUUID", course.getAuthor().getUUID());
        ArrayList<HashMap<String, Object>> lessonList = new ArrayList<>();
        for (Lesson l : course.getLessons()) {
            Map<String, Object> tMap = Map.of("title", l.getTitle(), "content", l.getContent(), "test", l.getTest() // TODO: don't write an objet to JSON, unless the lib supports that, still haven't tried
            );
            lessonList.add(new HashMap<>(tMap));
        }

        JSONObject allData;
        try (FileReader fileReader = new FileReader(COURSES_JSON)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException e) {
            return -2;
        } catch (ParseException e) {
            return -3;
        }

        var old = allData.put(courseUUID, courseData);

        // write back to json
        try (FileWriter fileWriter = new FileWriter(USERS_JSON)) {
            fileWriter.write(allData.toJSONString());
        } catch (IOException e) {
            return -2;
        }

        return (old == null) ? 1 : 0;
    }
}
