import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
     * Store's a user's progress and grades within a course
     *
     * @param userUUID user whose course progress is being written
     * @param course   course whose data is being saved
     * @return -1 on failure, else 0
     */
    public static int writeUserCourseData(UUID userUUID, Course course) {
        JSONObject courseData = new JSONObject();
        courseData.put("lessonsCompleted", course.getUserProgress());
        courseData.put("lessonGrades", course.fetchGrades());
        courseData.put("finalGrade", course.getFinalGrade());

        try(JSONWriter jsonWriter = new JSONWriter(USER_COURSE_DATA_JSON)) {
            return jsonWriter.atKey(userUUID.toString()).atKey(course.getUUID().toString()).write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
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
        for (Lesson l : course.getLessons()) {
            Test test = l.getTest();
            JSONArray testData = new JSONArray();
            for (Question q : test.getQuestions()) {
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

        try(JSONWriter jsonWriter = new JSONWriter(COURSES_JSON)) {
            return jsonWriter.atKey(course.getUUID().toString()).write(courseData);
        } catch (IOException | ParseException e) {
            return -1;
        }
    }
}
