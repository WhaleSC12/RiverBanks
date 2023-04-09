package testDruv;
import src.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.ArrayList;
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
       //needs to be completed make it pass
       Question question = new Question();
       String test = "Test Question in Test";
       ArrayList<AbstractMap.SimpleEntry<String, Boolean>>answerList = new ArrayList<>();
       answerList.add(new AbstractMap.SimpleEntry<>(test, true));
       ArrayList<Question> questions = new ArrayList<>();
       question.setAnswerList(answerList);
       questions.add(question);
       Quiz tests = new Quiz("Intro Test", "Used in testing case");
       tests.setQuestionList(questions);
       Lesson lessons = new Lesson("Testing", "Testing code", tests);
       assertEquals(lessons.getTest(),tests);
    }
     
     @Test
     public void getMadeTestTest() { 
        //need to be completed make it fall
        Question question = new Question();
       String test = "Test Question in Test";
       ArrayList<AbstractMap.SimpleEntry<String, Boolean>>answerList = new ArrayList<>();
       answerList.add(new AbstractMap.SimpleEntry<>(test, true));
       ArrayList<Question> questions = new ArrayList<>();
       question.setAnswerList(answerList);
       questions.add(question);
       Quiz tests = (Quiz) new Quiz("Intro Test", "Used in testing case");
       String testing = ((Course) tests).getTitle();
       tests.setQuestionList(questions);
       assertEquals(((Course) tests).getTitle(),testing);
     }

     @Test
     public void getQuestion() { 
        Question question = new Question();
       String test = "Test Question in Test";
       ArrayList<AbstractMap.SimpleEntry<String, Boolean>>answerList = new ArrayList<>();
       answerList.add(new AbstractMap.SimpleEntry<>(test, true));
       ArrayList<Question> questions = new ArrayList<>();
       question.setAnswerList(answerList);
       questions.add(question);
       String Qtest = question.getPrompt();
       assertEquals(question.getPrompt(), Qtest);
     }

}
