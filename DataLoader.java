/*
 * Data Load loads the users
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

;

public class DataLoader extends DataConstants {


    public static ArrayList<RegisteredUser> loadPeople() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader(USERS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) peopleJSON.get(i);
                String firstName = (String) userJSON.get(USERS_FIRST_NAME);
                String lastName = (String) userJSON.get(USERS_LAST_NAME);
                String email = (String) userJSON.get(USERS_EMAIL_STRING);
                String DOB = (String) userJSON.get(USERS_DOB);// have to change from type String to type Date
                String phoneNumber = (String) userJSON.get(USERS_PHONE_NUMBER);
                String userName = (String) userJSON.get(USERS_USER_NAME);
                String password = (String) userJSON.get(USERS_PASSWORD);
                /* got to change DOB type to Date and gotta ask Portia or a TA
                 * users.add(new RegisteredUser(firstName, lastName, email, DOB, phoneNumber, userName, password));
                 */
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Writes the user into users.json to be saved
     *
     * @param user the user to be written into storage
     * @return 0 on success, -1 if file not found, -2 if failed to parse existing data
     * @author Raymond
     */
    public static int writeUser(RegisteredUser user) {

        // NOTE: we should really just hash using UID as the key

        // doesn't currenlty encode lessondata
        JSONObject data = new JSONObject();
        data.put(USERS_FIRST_NAME, user.getFirstName());
        data.put(USERS_LAST_NAME, user.getLastName());
        data.put(USERS_EMAIL_STRING, user.getEmail());
        data.put(USERS_DOB, user.getDateOfBirth());
        data.put(USERS_PHONE_NUMBER, user.getPhoneNumber());
        data.put(USERS_USER_NAME, user.getUsername());
        data.put(USERS_PASSWORD, user.getPassword());
        JSONObject studentDat = new JSONObject();
        studentDat.put("id", user.getUID());
        data.put("Students", studentDat);

        // userData is basically the whole file
        JSONArray userData;
        try (FileReader reader = new FileReader(USERS_FILE_NAME)) {
            userData = (JSONArray) new JSONParser().parse(reader);
        } catch (IOException e) {
            return -1;
        } catch (ParseException e) {
            return -2;
        }

        // check if user already exists
        boolean found = false;
        for (int i = 0; i < userData.size(); i++) { // can't generify? :(
            JSONObject objectData = (JSONObject) userData.get(i);
            boolean isTeacher = (boolean) objectData.get("isTeacher");
            // not sure what to do about teachers since they have no id
            String studentID = (String) ((JSONObject) objectData.get("Students")).get("id");
            if (Objects.equals(user.getUID(), studentID)) {
                userData.set(i, data);
                found = true;
                break;
            }
        }
        if (!found) userData.add(data);

        return 0;
    }
}