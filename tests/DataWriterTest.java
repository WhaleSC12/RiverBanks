package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Module;
import src.*;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataWriterTest {

    @BeforeEach
    void setUp() {
        DataWriter.setUSERS_JSON("tests/dat/actual/users.json");
        DataWriter.setCOURSES_JSON("tests/dat/actual/courses.json");
        DataWriter.setUSER_COURSE_DATA_JSON("tests/dat/actual/usercourse.json");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newFile() {
        DataWriter.setUSERS_JSON("tests/dat/actual/new.json");

        DataWriter.writeUserData(new User(UUID.fromString("a1c1bf36-1793-496e-9d8b-e1ae9fd6573a"), "USERNAME",
                "PASSWORD", "FIRSTNAME", "LASTNAME", "EMAIL", "PHONENUMBER",
                "Student"));

        File file = new File("tests/dat/actual/new.json");
        assertTrue(file.delete());
    }


    @Test
    void writeUserData() {
        User user = new User(UUID.fromString("a1c1bf36-1793-496e-9d8b-e1ae9fd6573a"), "USERNAME",
                "PASSWORD", "FIRSTNAME", "LASTNAME", "EMAIL", "PHONENUMBER",
                "Student");
        DataWriter.writeUserData(user);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/users.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/users.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void writeUserDataCollection() {
        User user = new User(UUID.fromString("a1c1bf36-1793-496e-9d8b-e1ae9fd6573a"), "USERNAME",
                "PASSWORD", "FIRSTNAME", "LASTNAME", "EMAIL", "PHONENUMBER",
                "Student");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        DataWriter.writeUserData(userList);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/users.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/users.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void writeUserCourseData() {
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

        DataWriter.writeUserCourseData(userCourse);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/usercourse.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/usercourse.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void writeUserCourseDataCollection() {
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

        ArrayList<UserCourse> userCoursesList = new ArrayList<>();
        userCoursesList.add(userCourse);
        DataWriter.writeUserCourseData(userCoursesList);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/usercourse.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/usercourse.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void writeCourseData() {
        Course course = new Course(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COURSETITLE", "COURSEDESCRIPTION",
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), Language.Python);
        Quiz quiz = new Quiz("TESTTITLE", "TESTDESCRIPTION");
        Question question = new Question();
        question.setPrompt("QUESTIONPROMPT");
        question.getAnswerList().add(new AbstractMap.SimpleEntry<>("ANSWERTEXT", false));
        quiz.getQuestionList().add(question);
        Lesson lesson = new Lesson("LESSONTITLE", "LESSONDESCRIPTION", quiz);
        src.Module module = new Module("MODULETITLE", "MODULEDESCRIPTION", "MODULECONTENT");
        lesson.getModuleList().add(module);
        course.getLessonList().add(lesson);
        Comment comment = new Comment(UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COMMENTCONTENT");
        lesson.getCommentList().add(comment);

        DataWriter.writeCourseData(course);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/courses.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/courses.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    void writeCourseDataCollection() {
        Course course = new Course(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COURSETITLE", "COURSEDESCRIPTION",
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), Language.Python);
        Quiz quiz = new Quiz("TESTTITLE", "TESTDESCRIPTION");
        Question question = new Question();
        question.setPrompt("QUESTIONPROMPT");
        question.getAnswerList().add(new AbstractMap.SimpleEntry<>("ANSWERTEXT", false));
        quiz.getQuestionList().add(question);
        Lesson lesson = new Lesson("LESSONTITLE", "LESSONDESCRIPTION", quiz);
        src.Module module = new Module("MODULETITLE", "MODULEDESCRIPTION", "MODULECONTENT");
        lesson.getModuleList().add(module);
        course.getLessonList().add(lesson);
        Comment comment = new Comment(UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COMMENTCONTENT");
        lesson.getCommentList().add(comment);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);

        DataWriter.writeCourseData(courseList);

        ArrayList<User> actualList = DataLoader.getUsers("tests/dat/actual/courses.json");
        ArrayList<User> expectedList = DataLoader.getUsers("tests/dat/expected/courses.json");

        assertEquals(expectedList.toString(), actualList.toString());
    }
}