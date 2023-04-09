package src;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

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
     * Save the user's data (not course progress and grades) into the src.User JSON
     *
     * @param user user whose data should be saved
     */
    public static void writeUserData(User user) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            jsonWriter.atKey(user.getUUID().toString()).data(user).write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the user's data (not course progress and grades) into the src.User JSON
     * Iterates over any iterable collection of users
     *
     * @param userList list of users whose data should be saved
     */
    public static void writeUserData(Collection<User> userList) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            for (User user : userList) {
                jsonWriter
                        .atKey(user.getUUID().toString())
                        .data(user);
            }
            jsonWriter.write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userCourse object being written into JSON
     */
    public static void writeUserCourseData(UserCourse userCourse) {
        try (JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            jsonWriter
                    .atKey(userCourse.getUserUUID().toString())
                    .atKey(userCourse.getCourseUUID().toString())
                    .data(userCourse).write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userCourseCol collection of courses being written into JSON
     */
    public static void writeUserCourseData(Collection<UserCourse> userCourseCol) {
        try (JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            for (UserCourse userCourse : userCourseCol) {
                jsonWriter
                        .atKey(userCourse.userUUID.toString())
                        .atKey(userCourse.courseUUID.toString())
                        .data(userCourse);
                jsonWriter.emptyKeys();
            }
            jsonWriter.write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's a user's progress and grades within a course
     *
     * @param userCourseDataMap map of courses being written into JSON
     */
    public static void writeUserCourseData(HashMap<UUID, HashMap<UUID, UserCourse>> userCourseDataMap) {
        try (JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            for (var entry : userCourseDataMap.entrySet()) {
                jsonWriter.atKey(entry.getKey().toString());
                for (var v : entry.getValue().entrySet()) {
                    jsonWriter
                            .atKey(v.getKey().toString())
                            .data(v.getValue());
                }
                jsonWriter.emptyKeys();
            }
            jsonWriter.write();
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
            jsonWriter.atKey(course.getUUID().toString()).data(course).write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store's course information, such as lessons, content, tests, etc. into the courses json
     *
     * @param courseList list of courses whose data is being saved
     */
    public static void writeCourseData(ArrayList<Course> courseList) {

        try (JSONWriter jsonWriter = new JSONWriter(COURSES_JSON)) {
            for (Course c : courseList) {
                jsonWriter.atKey(c.getUUID().toString()).data(c);
            }
            jsonWriter.write();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * General use save which calls all the prior save functions using the singleton structures in the project
     */
    public static void saveAll() {
        UserData userData = UserData.getInstance();
        writeUserData(userData.userList);
        UserCourseData userCourseData = UserCourseData.getInstance();
        writeUserCourseData(userCourseData.courseDataList);
        CourseData courseData = CourseData.getInstance();
        writeCourseData(courseData.courseList);
    }
}
