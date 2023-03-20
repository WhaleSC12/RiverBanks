import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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

    public static HashMap<Object, Object> fetchData(String file) {
        JSONObject data = fetchRoot(file);
        if (data == null) {
            return null; // TODO: custom exception
        }
        return new HashMap<Object, Object>(data);
    }

}