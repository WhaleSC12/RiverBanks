import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Singleton which holds a list of all user objects
 */
public class UserData {
    private static UserData instance;
    public final ArrayList<User> userList;

    private UserData(ArrayList<User> userList) {
        this.userList = userList;
    }

    public static UserData getInstance() {
        if (instance == null) instance = new UserData(DataLoader.getUsers());
        return instance;
    }

    public User getUser(String username) {
        for (User u : userList) {
            if (Objects.equals(u.getUsername(), username)) return u;
        }
        return null;
    }

    public User getUser(UUID uuid) {
        for (User u : userList) {
            if (uuid.equals(u.getUUID())) return u;
        }
        return null;
    }

}
