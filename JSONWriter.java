import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
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
        fileWriter = new FileWriter(filePath);
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
     * Empties the key list so that traversal can being from root without recreating JSONWriter
     */
    public void emptyKeys() {
        keys.clear();
    }

    /**
     * Traverses the key list upwards count number of times
     *
     * @param count Number of keys to reverse
     * @return self for method chain
     */
    public JSONWriter backKey(int count) {
        if (keys.size() > keys.size() - 1 - count + 1) {
            keys.subList(keys.size() - 1 - count + 1, keys.size()).clear();
        }
        return this;
    }

    /**
     * Put the data at newData into the JSON object created from the old file
     * To write with this, an Object must be JSONAware. This can either be done by manually filling a new JSONObject
     * or JSONArray with values, or by implementing the JSONAware interface.
     * Removes the last key, done for the purpose of chaining data at the same key.
     *
     * @param newData JSONAware object to be written into the file opened on instantiation of this writer
     * @return self for method chaining (IMPORTANT! - the key last entry of the key list is removed)
     */
    public JSONWriter data(JSONAware newData) {
        currentPlace = root;
        for (int i = 0; i < keys.size() - 1; i++) {
            if (!currentPlace.containsKey(keys.get(i))) currentPlace.put(keys.get(i), new JSONObject());
            currentPlace = (JSONObject) currentPlace.get(keys.get(i));
        }
        currentPlace.put(keys.get(keys.size() - 1), newData);

        keys.remove(keys.size() - 1);
        return this;
    }

    /**
     * Writes the data in the JSONWriter
     * This closes the file, as a new filewriter must be created to write again
     */
    public void write() throws IOException {
        try {
            root.writeJSONString(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.close();
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
    }
}
