import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class DataWriter {
    private static final String userPersonalData = "json/dat/users.json"; // to point refs later more easily
    private static final String userCourseData = "json/dat/userCourseData.json";

    /**
     * Writes the user into users.json to be saved
     *
     * @param user the user to be written into storage
     * @return 1 if newly created, 0 if overwrote existing, -1 if user does not exist, -2 if failed to open file, -3 if failed to parse json
     * @author Raymond
     */
    public static int writeUser(User user) {

        // putting all references to RegisteredUser user at top to improve readability
        UUID userUUID = user.getUUID();
        HashMap<String, Object> tempData = new HashMap<>();
        tempData.put("firstName", user.getFirstName());
        tempData.put("lastName", user.getLastName());
        tempData.put("email", user.getEmail());
        tempData.put("phoneNumber", user.getPhoneNumber());
        tempData.put("clearance", user.getClearance());
        tempData.put("username", user.getUsername());
        tempData.put("password", user.getPassword());

        // yoink data
        JSONObject allData;
        try (FileReader fileReader = new FileReader(userPersonalData)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException e) {
            return -2;
        } catch (ParseException e) {
            return -3;
        }

        // outdated library using raw data. nice yellow highlight for me. lovely.
        // note this will simply create a new entry if the current UUID does not exist
        // and if the uuid does exist, old will have the old value, else null
        var old = allData.put(userUUID, tempData);

        // not currently certain how we're expected to handle bad filepaths, but i imagine just outright crashing the process is the wrong answer,
        // but maybe we should let whatever is using the utility handle that

        // write back to json
        try (FileWriter fileWriter = new FileWriter(userPersonalData)) {
            fileWriter.write(allData.toJSONString());
        } catch (IOException e) {
            return -2;
        }

        // old will be null if there was no existing data at allData.userUUID
        return (old == null) ? 1 : 0;
    }

    public static int storeCourseProgress(Student user, Course course) {
        UUID userUUID = user.getUUID();
        HashMap<String, Object> courseData = new HashMap<>();
        courseData.put("courseUUID", course.getUUID());
        courseData.put("lessonsCompleted", course.getUserProgress());
        courseData.put("lessonGrades", course.fetchGrades());
        courseData.put("finalGrade", course.getFinalGrade());

        // yoink data again, yes I didn't abstract this, maybe I'll do it later -Raymond
        JSONObject allData;
        try (FileReader fileReader = new FileReader(userCourseData)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException e) {
            return -2;
        } catch (ParseException e) {
            return -3;
        }

        JSONArray userCoursesDataArray = (JSONArray) allData.get(userUUID);

        userCoursesDataArray.add(courseData);
        // repeated try-catch code isn't real it can't hurt you aaaaaaaaaaaaa
        var old = allData.put(userUUID, userCoursesDataArray);

        // write back to json
        try (FileWriter fileWriter = new FileWriter(userPersonalData)) {
            fileWriter.write(allData.toJSONString());
        } catch (IOException e) {
            return -2;
        }

        return (old == null) ? 1 : 0;
    }
}
