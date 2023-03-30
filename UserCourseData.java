import java.util.HashMap;
import java.util.UUID;

/**
 * Singleton which holds a map of all user course objects
 */
public class UserCourseData {
    private static UserCourseData instance;
    public final HashMap<UUID, HashMap<UUID, UserCourse>> courseDataList;

    private UserCourseData(HashMap<UUID, HashMap<UUID, UserCourse>> courseDataList) {
        this.courseDataList = courseDataList;
    }

    public static UserCourseData getInstance() {
        if (instance == null) instance = new UserCourseData(DataLoader.getUserCourses());
        return instance;
    }

    public UserCourse getUserCourse(UUID userUUID, UUID courseUUID) {
        return courseDataList.get(userUUID).get(courseUUID);
    }
}
