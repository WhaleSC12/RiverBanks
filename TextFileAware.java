/**
 * Used for ToTextFile to ensure it can only be called on classes capable of being written.
 * Also allows for easy addition of other classes to be written without changing functionality.
 */
public interface TextFileAware {
    String toFileString();

    String getFileName();
}
