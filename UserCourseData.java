import java.util.HashMap;
import java.util.UUID;

/**
 * Singleton which holds a map of all user course objects
 */
public class UserCourseData {
    private static UserCourseData instance;
    public final HashMap<UUID, HashMap<UUID, UserCourse>> userCourseData;

    private UserCourseData(HashMap<UUID, HashMap<UUID, UserCourse>> userCourseData) {
        this.userCourseData = userCourseData;
    }

    public static UserCourseData getInstance() {
        if (instance == null) instance = new UserCourseData(DataLoader.getUserCourses());
        return instance;
    }

    public UserCourse getUserCourseData(UUID userUUID, UUID courseUUID) {
        return userCourseData.get(userUUID).get(courseUUID);
    }
}
