package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

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
        assertEquals(user1.toString() + user2.toString(), userData.get(0).toString() + userData.get(2).toString());
    }

    @Test
    void getCourses() {
        var courseData = DataLoader.getCourses("tests/dat/courses.json");

        Course course = new Course(
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), "COURSETITLE","COURSEDESCRIPTION",
                UUID.fromString("683172b6-86a0-439c-9993-1265214cf909"), Language.Python);
        Lesson lesson1 = new Lesson("LESSON1","LESSONDESCRIPTION",);


        assertEquals(course.toString(), courseData.get(0).toString(), courseData.get(0).toString());
    }

    @Test
    void getUserCourses() {
    }
}