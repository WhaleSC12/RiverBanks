import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class DataLoader {

    private static JSONObject fetchRoot(String file) {
        try (FileReader fileReader = new FileReader(file)) {
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException | ParseException ignored) {
            // TODO: someone handle the exception in a better way
        }
        return null;
    }

    public static class UserData {
        private static HashMap<String, HashMap<String, String>> userData;

        private static HashMap<String, HashMap<String, String>> getUserData() {
            if (userData != null) return userData;
            JSONObject root = fetchRoot("json/dat/users.json");
            userData = (HashMap<String, HashMap<String, String>>) new HashMap(root);
            return userData;
        }

        public User getUser(String username) {
            for (var entry : userData.entrySet()) {
                var val = entry.getValue();
                if (Objects.equals(val.get("username"), username))
                    return new User(UUID.fromString(entry.getKey()), val.get("username"),
                            val.get("password"), val.get("firstName"), val.get("lastName"),
                            val.get("email"), val.get("phoneNumber"), val.get("clearance"));
            }
            return null;
        }

        public User getUser(UUID uuid) {
            var val = userData.get("uuid");
            return new User(uuid, val.get("username"),
                    val.get("password"), val.get("firstName"), val.get("lastName"),
                    val.get("email"), val.get("phoneNumber"), val.get("clearance"));
        }
    }

    public static class CourseData {
        private static JSONObject courseData;
    }

    public static class UserCourseData {

    }
}