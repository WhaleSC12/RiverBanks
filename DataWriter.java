import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
     * @return -1 on failure, 0 otherwise
     */
    public static int writeUserData(User user) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            return jsonWriter.atKey(user.getUUID().toString()).write(user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the user's data (not course progress and grades) into the User JSON
     * Iterates over any iterable collection of users
     *
     * @param userList list of users whose data should be saved
     * @return -1 on failure, 0 otherwise
     */
    public static int writeUserData(Collection<User> userList) {
        try (JSONWriter jsonWriter = new JSONWriter(USERS_JSON)) {
            for (User user : userList) {
                jsonWriter.atKey(user.getUUID().toString()).write(user);
            }
            return 0;
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
                jsonWriter.atKey(userCourseData.userUUID.toString()).atKey(userCourseData.courseUUID.toString()).write(userCourseData);
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
     * @return -1 on failure, else 0
     */
    public static int writeCourseData(Course course) {
        JSONObject courseData = new JSONObject();
        courseData.put("courseTitle", course.getTitle());
        courseData.put("description", course.getDescription());
        courseData.put("language", course.getLanguage());
        courseData.put("authorUUID", course.getAuthorUUID().toString());
        JSONArray lessonList = new JSONArray();
        for (Course.Lesson l : course.getLessons()) {
            Course.Lesson.Test test = l.getTest();
            JSONArray testData = new JSONArray();
            for (Course.Lesson.Test.Question q : test.getQuestions()) {
                JSONObject questionData = new JSONObject();
                questionData.put("prompt", q.getPrompt());
                JSONArray answersArr = new JSONArray();
                for (var v : q.getAnswerList()) {
                    JSONObject answerData = new JSONObject();
                    answerData.put(v.getKey(), v.getValue() ? 1 : 0);
                    answersArr.add(answerData);
                }
                questionData.put("answers", answersArr);
                testData.add(questionData);
            }
            Map<String, Object> tMap = Map.of("title", l.getTitle(), "content", l.getContent(), "test", testData);
            lessonList.add(new HashMap<>(tMap));
        }
        courseData.put("lessons", lessonList);

        try (JSONWriter jsonWriter = new JSONWriter(COURSES_JSON)) {
            return jsonWriter.atKey(course.getUUID().toString()).write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }
}
