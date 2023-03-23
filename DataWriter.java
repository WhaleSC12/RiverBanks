import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Collection;

/**
 * Used to write user and course data into their respective JSONs.
 * This is achieved by pulling the entire JSON file into memory,
 * replacing the part we want saved with new data,
 * then writing back to the JSON.
 */
public class DataWriter {
    private static final String USERS_JSON = "json/dat/users.json"; // to point refs later more easily, ideally we use a singleton or something
    private static final String USER_COURSE_DATA_JSON = "json/dat/userCourseData.json";
    private static final String COURSES_JSON = "json/dat/courses.json";

    /**
     * Save the user's data (not course progress and grades) into the User JSON
     *
     * @param user user whose data should be saved
     */
    public static void writeUserData(User user) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            jsonWriter.atKey(user.getUUID().toString()).write(user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the user's data (not course progress and grades) into the User JSON
     * Iterates over any iterable collection of users
     *
     * @param userList list of users whose data should be saved
     */
    public static void writeUserData(Collection<User> userList) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            for (User user : userList) {
                jsonWriter.atKey(user.getUUID().toString()).write(user);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userCourseData object being written into JSON
     */
    public static void writeUserCourseData(UserCourseData userCourseData) {
        try (JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            jsonWriter
                    .atKey(userCourseData.getUserUUID().toString())
                    .atKey(userCourseData.getCourseUUID().toString())
                    .write(userCourseData);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userCourseDataList collection of courses being written into JSON
     */
    public static void writeUserCourseData(Collection<UserCourseData> userCourseDataList) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            for (UserCourseData userCourseData : userCourseDataList) {
                jsonWriter
                        .atKey(userCourseData.userUUID.toString())
                        .atKey(userCourseData.courseUUID.toString())
                        .write(userCourseData);
                jsonWriter.emptyKeys();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's course information, such as lessons, content, tests, etc. into the courses json
     *
     * @param course course whose data is being stored
     */
    public static void writeCourseData(Course course) {

        try (JSONWriter jsonWriter = new JSONWriter(COURSES_JSON)) {
            jsonWriter.atKey(course.getUUID().toString()).write(course);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
