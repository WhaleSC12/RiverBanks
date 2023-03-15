import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataWriter {
    private static final String UserFilePath = "json/dat/users.json"; // to point refs later more easily

    /**
     * Writes the user into users.json to be saved
     *
     * @param user the user to be written into storage
     * @return 0 on success, -1 if user does not exist, -2 if failed to open file, -3 if failed to parse json
     * @author Raymond
     */
    public static int writeUser(RegisteredUser user) {

        // putting all references to RegisteredUser user at top to improve readability
        String userUUID = user.getUID();
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
        try (FileReader fileReader = new FileReader(UserFilePath)) {
            allData = (JSONObject) new JSONParser().parse(fileReader);
        } catch (IOException e) {
            return -2;
        } catch (ParseException e) {
            return -3;
        }

        // lovely. outdated library using raw data. nice yellow highlight for me.
        // note this will simply create a new entry if the current UUID does not exist
        allData.put(userUUID, tempData);

        // write back to json
        try (FileWriter fileWriter = new FileWriter(UserFilePath)) {
            fileWriter.write(allData.toJSONString());
        } catch (IOException e) {
            return -2;
        }

        return 0;
    }
}
