package testDruv;
import src.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDataTest { 

    @BeforeClass
    public static void oneTimeSetup()
    { 

    }

    @AfterClass
    public void oneTimeTearDown() { 

    }

    @BeforeEach
    public void setup(){ 

    }

    @AfterEach
    public void tearDown()
    { 

    }
@Test
    public void getCourseTitle(){ 
       //works 
       Course course = new Course("Testing intro", "created to Test courseData",UUID.randomUUID() , Language.Java);
        
        String test = "Testing intro";
        assertEquals(course.getTitle(),test);
    }

    @Test
    public void getCourseDescription(){
        //works 
        Course course = new Course("Testing intro", "created to Test courseData",UUID.randomUUID() , Language.Java);
         
        String test = "created to Test courseData";
         assertEquals(course.getDescription(),test);
     }

     @Test
    public void getCourseUUID(){
        //works 
        Course course = new Course("Testing intro", "created to Test courseData",UUID.randomUUID() , Language.Java);
         UUID test = course.getUUID();
         assertEquals(course.getUUID(),test);
     }

     @Test
    public void getCouseLanguage(){
        //works 
        Course course = new Course("Testing intro", "created to Test courseData",UUID.randomUUID() , Language.Java);
         Language test = Language.Java;
         assertEquals(course.getLanguage(),test);
     }
     
     @Test
     public void getLessonTest(){ 
        //need to be completed
     }
     
     @Test
     public void getTestTest() { 
        //need to be completed
     }

}
