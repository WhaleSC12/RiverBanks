import java.util.ArrayList;
import java.util.UUID;

public class DataWriterTest {
    public static void main(String[] args) {
        UUID testUUID = UUID.fromString("8c3eedf7-ae54-4543-b60e-30750467b1d1");
        User testUser = new User(testUUID, "username2",
                "password", "first name",
                "last name", "email",
                "phone number", "clearance");
        DataWriter.writeUserData(testUser);

        Course testCourse = new Course("course title", "course desc", testUUID, Language.Cpp);
        Course.Lesson.Test test = new Course.Lesson.Test(); //TODO: see
        Course.Lesson lesson = new Course.Lesson("lesson title", "lesson description", "lesson content", test);
        testCourse.addLesson(lesson);

        ArrayList<Double> lessonGrades = new ArrayList<>();
        lessonGrades.add(100.0);
        lessonGrades.add(90.0);
        lessonGrades.add(80.0);
        lessonGrades.add(70.0);
        lessonGrades.add(60.0);
        UserCourseData userCourseData = new UserCourseData(testUUID, UUID.randomUUID(), 5, lessonGrades);
        DataWriter.writeUserCourseData(userCourseData);
    }
}
