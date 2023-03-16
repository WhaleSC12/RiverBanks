/*
 * Data Load loads the users
 */

import java.time.LocalDate;
import java.util.Locale;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

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
                
                 users.add(new RegisteredUser(firstName, lastName, email, DOB, phoneNumber, userName, password));
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}