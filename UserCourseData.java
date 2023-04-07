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

    /**
     * @return This returns an instance of the class, rather than just the list, for the purpose of implementing
     * helper functions without needing to update them across multiple classes
     */
    public static UserCourseData getInstance() {
        if (instance == null) instance = new UserCourseData(DataLoader.getUserCourses("json/dat/userCourseData.json"));
        return instance;
    }

    /**
     * @param userUUID UUID of the user being searched for
     * @param courseUUID UUID of the course being searched for
     * @return the course matching the user and course uuids
     */
    public UserCourse getUserCourse(UUID userUUID, UUID courseUUID) {
        return courseDataList.get(userUUID).get(courseUUID);
    }
}
