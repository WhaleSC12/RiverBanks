/**
 * Used for ToTextFile to ensure it can only be called on classes capable of being written.
 * Also allows for easy addition of other classes to be written without changing functionality.
 */
public interface TextFileAware {
    /**
     * This function will be called when attempting to write a TextFileAware object to a text file
     *
     * @return String will be written exactly as it is into a text file
     */
    String toFileString();

    /**
     * This function will be called when naming the file the text will be written to.
     * The file name will be getFileName()+".txt", so exclude the file extension from the return.
     *
     * @return the file name (excluding file extension) the written object should have
     */
    String getFileName();
}
