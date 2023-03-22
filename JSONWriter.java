import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Writes into the given file at a certain point. Created for use with JSON files structured around Hashmaps,
 * does not support adding arrays
 * Use .atKey() to traverse down from the root until you reach the key whose value you wish to replace,
 * then use .write() to write in the data you want at that value
 */
public class JSONWriter implements AutoCloseable {
    private final JSONObject root;
    private JSONObject currentPlace;
    private final ArrayList<String> keys = new ArrayList<>();
    private final FileWriter fileWriter;

    /**
     * @param filePath JSON file which contains the object being written into
     * @throws IOException    if the given file does not exist, IOException is thrown
     * @throws ParseException if the given JSON cannot be parsed, ParseException is thrown
     */
    public JSONWriter(String filePath) throws IOException, ParseException {
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
    public JSONWriter atKey(String key) {
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
    public int write(JSONAware newData) {
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
