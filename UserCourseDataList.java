import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserCourseDataList {
    private static UserCourseDataList instance;
    private final HashMap<UUID,HashMap<UUID,UserCourseData>> userCourseData;
    private UserCourseDataList(HashMap<UUID, HashMap<UUID, UserCourseData>> userCourseData) {
        this.userCourseData = userCourseData;
    }

    public static UserCourseDataList getInstance() {
        if (instance == null) instance = new UserCourseDataList(DataLoader.getUserCourses());
        return instance;
    }

    public UserCourseData getUserCourseData(UUID userUUID, UUID courseUUID) {
        return userCourseData.get(userUUID).get(courseUUID);
    }
}
