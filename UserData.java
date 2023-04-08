import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

//imports added for testing 
import static org.junit.jupiter.api.Assertions.*;
/**
 * Singleton which holds a list of all user objects
 */
public class UserData {
    private static UserData instance;
    public final ArrayList<User> userList;

    private UserData(ArrayList<User> userList) {
        this.userList = userList;
    }

    /**
     * @return an instance of the class object instead of the arraylist for the purpose of maintaining helper functions
     * without the need to update multiple files in the event o a data structure change
     */
    public static UserData getInstance() {
        if (instance == null) instance = new UserData(DataLoader.getUsers("json/dat/users.json"));
        return instance;
    }

    /**
     * returns a user object matching the given username created from existing data
     *
     * @param username username of the user being searched for
     * @return user object matching the username
     */
    public User getUser(String username) {
        for (User u : userList) {
            if (Objects.equals(u.getUsername(), username)) return u;
        }
        return null;
    }

    /**
     * returns a user object matching the uuid created from existing data
     *
     * @param uuid uuid of the user being searched for
     * @return user object matching the uuid matching existing data
     */
    public User getUser(UUID uuid) {
        for (User u : userList) {
            if (uuid.equals(u.getUUID())) return u;
        }
        return null;
    }

}
