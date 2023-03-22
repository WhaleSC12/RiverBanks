import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
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

    public static class UserData {
        private static HashMap<String, HashMap<String, String>> userData;

        private static HashMap<String, HashMap<String, String>> getUserData() {
            if (userData != null) return userData;
            JSONObject root = fetchRoot("json/dat/users.json");
            userData = (HashMap<String, HashMap<String, String>>) new HashMap(root);
            return userData;
        }

        public static User getUser(String username) {
            var tmp = getUserData();
            for (var entry : tmp.entrySet()) {
                var val = entry.getValue();
                if (Objects.equals(val.get("username"), username))
                    return new User(UUID.fromString(entry.getKey()), val.get("username"), val.get("password"), val.get("firstName"), val.get("lastName"), val.get("email"), val.get("phoneNumber"), val.get("clearance"));
            }
            return null;
        }

        public static User getUser(UUID uuid) {
            var tmp = getUserData();
            var val = tmp.get("uuid");
            return new User(uuid, val.get("username"), val.get("password"), val.get("firstName"), val.get("lastName"), val.get("email"), val.get("phoneNumber"), val.get("clearance"));
        }
    }

    public static class CourseData {
        private static HashMap<String, HashMap<String, String>> userData;

        private static HashMap<String, HashMap<String, String>> getUserData() {
            if (userData != null) return userData;
            JSONObject root = fetchRoot("json/dat/courses.json");
            userData = (HashMap<String, HashMap<String, String>>) new HashMap(root);
            return userData;
        }

        public static Course getCourse(UUID uuid) {
            var tmp = getUserData();
            var val = tmp.get("uuid");
//            return new User(uuid, val.get("username"), val.get("password"), val.get("firstName"), val.get("lastName"), val.get("email"), val.get("phoneNumber"), val.get("clearance"));
            return null;
            // TODO: simplify course constructor
        }
    }

    public static class UserCourseData {
        private static HashMap<String, HashMap<String, String>> userData;

        private static HashMap<String, HashMap<String, String>> getUserData() {
            if (userData != null) return userData;
            JSONObject root = fetchRoot("json/dat/userCourses.json");
            userData = (HashMap<String, HashMap<String, String>>) new HashMap(root);
            return userData;
        }
    }
}
