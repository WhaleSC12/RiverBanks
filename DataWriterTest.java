import java.util.AbstractMap;
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
        Test test = new Test();
        Question question = new Question();
        question.setPrompt("prompt");
        ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();
        AbstractMap.SimpleEntry<String, Boolean> answer = new AbstractMap.SimpleEntry<>("answer", false);
        answerList.add(answer);
        question.setAnswerList(answerList);
        ArrayList<Question> questionList = new ArrayList<>();
        questionList.add(question);
        test.setQuestionList(questionList);
        Lesson lesson = new Lesson("lesson title", "lesson description", test);
        testCourse.addLesson(lesson);
        DataWriter.writeCourseData(testCourse);

        ArrayList<Double> lessonGrades = new ArrayList<>();
        lessonGrades.add(100.0);
        lessonGrades.add(90.0);
        lessonGrades.add(80.0);
        lessonGrades.add(70.0);
        lessonGrades.add(60.0);
        UserCourse userCourse = new UserCourse(testUUID, UUID.randomUUID(), 5, lessonGrades);
        DataWriter.writeUserCourseData(userCourse);
    }
}
