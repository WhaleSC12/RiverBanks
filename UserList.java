import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class UserList {
    private static UserList instance;
    private final ArrayList<User> userList;

    private UserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public static UserList getInstance() {
        if (instance == null) instance = new UserList(DataLoader.getUsers());
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
            if (u.getUUID() == uuid) return u;
        }
        return null;
    }
}
