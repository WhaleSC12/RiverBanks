import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Singleton which holds a list of all user objects
 */
public class UserData {
    private static UserData instance;
    public final ArrayList<User> userData;

    private UserData(ArrayList<User> userData) {
        this.userData = userData;
    }

    public static UserData getInstance() {
        if (instance == null) instance = new UserData(DataLoader.getUsers());
        return instance;
    }

    public User getUser(String username) {
        for (User u : userData) {
            if (Objects.equals(u.getUsername(), username)) return u;
        }
        return null;
    }

    public User getUser(UUID uuid) {
        for (User u : userData) {
            if (uuid.equals(u.getUUID())) return u;
        }
        return null;
    }

}
