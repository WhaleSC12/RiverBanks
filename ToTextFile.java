import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for writing data into text files using TextFileAware interface
 */
public class ToTextFile {
    /**
     * Writes the result of toFileString to a file under the name getFileName.txt
     *
     * @param data Any class implementing TextFileAware which will be written to a file
     */
    public static void write(TextFileAware data) {
        try (FileWriter fileWriter = new FileWriter(data.getFileName() + ".txt")) {
            fileWriter.write(data.toFileString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
