package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Module;
import src.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() {
        var userData = DataLoader.getUsers("tests/dat/users.json");
        User user1 = new User(
                UUID.fromString("a1c1bf36-1793-496e-9d8b-e1ae9fd6573a"), "USERNAME", "PASSWORD",
                "FIRSTNAME", "LASTNAME", "EMAIL", "PHONENUMBER", "Student");
        User user2 = new User(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "USERNAME2", "PASSWORD2",
                "FIRSTNAME2", "LASTNAME2", "EMAIL2", "PHONENUMBER2", "Student2");
        assertEquals(user1.toString() + user2.toString(), userData.get(0).toString() + userData.get(1).toString());
    }

    @Test
    void getCourses() {
        var courseData = DataLoader.getCourses("tests/dat/courses.json");

        Course course = new Course(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COURSETITLE", "COURSEDESCRIPTION",
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), Language.Python);
        Quiz quiz = new Quiz("TESTTITLE", "TESTDESCRIPTION");
        Question question = new Question();
        question.setPrompt("QUESTIONPROMPT");
        question.getAnswerList().add(new AbstractMap.SimpleEntry<>("ANSWERTEXT", false));
        quiz.getQuestionList().add(question);
        Lesson lesson = new Lesson("LESSONTITLE", "LESSONDESCRIPTION", quiz);
        Module module = new Module("MODULETITLE", "MODULEDESCRIPTION", "MODULECONTENT");
        lesson.getModuleList().add(module);
        course.getLessonList().add(lesson);
        Comment comment = new Comment(UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COMMENTCONTENT");
        lesson.getCommentList().add(comment);

        assertEquals(course.toString(), courseData.get(0).toString(), courseData.get(0).toString());
    }

    @Test
    void getUserCourses() {
        var userCourses = DataLoader.getUserCourses("tests/dat/usercourse.json");

        ArrayList<Double> grades = new ArrayList<>(Arrays.asList(
                100.0,
                100.0,
                100.0,
                100.0,
                100.0,
                100.0,
                100.0,
                100.0,
                100.0,
                1.0,
                100.0));
        UserCourse userCourse = new UserCourse(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"),
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), 11, grades);

        assertEquals(userCourse.toString(), userCourses.get(UUID.fromString("683172b6-86a0-439c-9993-1265214cf909")).get(UUID.fromString("683172b6-86a0-439c-9993-1265214cf909")).toString());
    }
}