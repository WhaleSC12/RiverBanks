import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.UUID;

import java.util.Scanner;


//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User currentUser;
    private static final UserData userData = UserData.getInstance();
    private UserData currenUserData;
    private Course course;
    private Module module;
    private Lesson lesson;
    private Test test;
    private Question question;
    Scanner scanner = new Scanner(System.in); 

/**
 * 
 * @param username      username used to look for person
 * @param password      password used to secure the User to Login
 * @return
 */

    public User Login(String username, String password) 
    {
        for(User u : userData.userList) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) { 
                return userData.getUser(username);
            }
        }
        return null;
    }

/**
 * Takes in the users info and creates a new Login for the user and generates them a unique UUID and then adds them to the UserList
 * @param username    Username the user uses to log in
 * @param password    Password the user uses to log in
 * @param firstName   User's first name
 * @param lastName    User's last name
 * @param email       User's email address
 * @param phoneNumber User's phone number
 * @param clearance   User's clearance level
 */
    public void createLogin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) 
    {
        currentUser = new User(username, password, firstName, lastName, email, phoneNumber, clearance);
        userData.userList.add(currentUser);
    }

    /**  search function that searches by name of course
     * @param bool   returns true if course is there
     */
    public boolean searchCourse(String courseName) 
    {
        return false;
    }
/** void method that allows Teacher to create Lesson 
* asking user to enter name,description,lanuage and amount of lessons for the course and then
* asks the user how many lessons they want and if they want to add a Test to it and write the test with 
* the questions and answer choices
* @param course       basic course info and holds all lessons
* @param Lesson       basic lesson info such as name and description and hold all test
* @param test         basic test info such as name and description and hools all questions
* @param question     basic question info such as question and answers choices and holds correct choice
*/public  Course createCourse(UUID uuid, String title, String description, UUID authorUUID, Language language)
{ 
   Course course = new Course(title, description, authorUUID, language);
    
   System.out.print("Enter the number of modules: ");
   int numModules = Integer.parseInt(scanner.nextLine());

   ArrayList<Module> modules = new ArrayList<>();
   for (int i = 0; i < numModules; i++) {
       System.out.println("Creating module " + (i + 1) + "...");
       System.out.print("Enter the module name: ");
       String moduleName = scanner.nextLine();

       System.out.print("Enter the module description: ");
       String moduleDescription = scanner.nextLine();

       System.out.print("Enter the module content: ");
       String moduleContent = scanner.nextLine();

       System.out.print("Enter the number of lessons: ");
       int numLessons = Integer.parseInt(scanner.nextLine());

       ArrayList<Lesson> lessons = new ArrayList<>();
       for (int j = 0; j < numLessons; j++) {
           System.out.println("Creating lesson " + (j + 1) + "...");
           System.out.print("Enter the lesson name: ");
           String lessonName = scanner.nextLine();

           System.out.print("Enter the lesson description: ");
           String lessonDescription = scanner.nextLine();

           System.out.print("Enter the number of tests: ");
           int numTests = Integer.parseInt(scanner.nextLine());
// creating tests 
           ArrayList<Test> tests = new ArrayList<>();
           for (int k = 0; k < numTests; k++) {
               System.out.println("Creating test " + (k + 1) + "...");
               System.out.print("Enter the test name: ");
               String testName = scanner.nextLine();

               System.out.print("Enter the test description: ");
               String testDescription = scanner.nextLine();

               System.out.print("Enter the number of questions: ");
               int numQuestions = Integer.parseInt(scanner.nextLine());
//creating questions 
               ArrayList<Question> questions = new ArrayList<>();
               for (int l = 0; l < numQuestions; l++) {
                Question question = new Question();
                   System.out.println("Creating question " + (l + 1) + "...");
                   System.out.print("Enter the question prompt: ");
                   String prompt = scanner.nextLine();
                   question.setPrompt(prompt);
                   
                   System.out.print("Enter the number of answer choices: ");
                   int numChoices = Integer.parseInt(scanner.nextLine());
                   ArrayList<AbstractMap.SimpleEntry<String,Boolean>> answerList = new ArrayList<>();

                   for (int m = 0; m < numChoices; m++) {
                      System.out.println("Enter the text for answer choice " + (m+1) + ":");
                      String answerString = scanner.nextLine();

                      System.out.println("Is this anser choice correct? (true/false):");
                      boolean isCorrect = scanner.nextBoolean();
                      answerList.add(new AbstractMap.SimpleEntry<>(answerString, isCorrect));
                   }
                   question.setAnswerList(answerList);
                   questions.add(question);
               }

               Test test = new Test(testName, testDescription);
               tests.add(test);
           }

           Lesson l = new Lesson(lessonName, lessonDescription, test);
           lessons.add(l);
       }

       Module m = new Module(moduleName, moduleDescription, moduleContent);
       modules.add(m);
   }

   Course c = new Course(title, description, authorUUID, language);
   return c;
}



    /*
     * sets all parameters to null and logs the user out
     * @return null
     */
    public void Logout() {
        currentUser.equals(null);
    }

}


/**
 * public void createCourse() 
    {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter Course title");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course language: ");
        Language language = Language.valueOf(scanner.nextLine().toUpperCase());
        UUID authorUUID = UUID.randomUUID();
        System.out.print("Enter many lessons would you like for your course: ");
        int numLessons = scanner.nextInt();

        Course course = new Course(title, description, authorUUID, language);


        for (int i = 1; i <= numLessons; i++) {
            System.out.printf("Lesson %d:\n", i);
            System.out.print("Enter lesson title: ");
            String lessonTitle = scanner.nextLine();
            System.out.print("Enter lesson description: ");
            String lessonDescription = scanner.nextLine();
            System.out.print("Enter lesson content: ");
            String lessonContent = scanner.nextLine();

            ArrayList<Test> tests = new ArrayList<>();

            while (true) {
                System.out.print("Add a test? (y/n): ");
                String addTest = scanner.nextLine();
                if (addTest.equalsIgnoreCase("n")) {
                    break;
                }
                System.out.print("Enter test title: ");
                String testTitle = scanner.nextLine();
                System.out.print("Enter test description: ");
                String testDescription = scanner.nextLine();
                Test test = new Test(testTitle, testDescription);

                ArrayList<Question> questions = new ArrayList<>();
                while (true) {
                    System.out.print("Add a question? (y/n): ");
                    String addQuestion = scanner.nextLine();
                    if (addQuestion.equalsIgnoreCase("n")) {
                        break;
                    }
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    System.out.print("Enter answer: ");
                    String answer = scanner.nextLine();
                    Question question = new Question();
                    questions.add(question);
                }

                test.setQuestionList(questions);
                tests.add(test);
            }

            Lesson lesson = new Lesson(lessonTitle, lessonDescription, lessonContent);
            module.setTest(test);
            course.addLesson(lesson);
            
        }

        System.out.println("Course created:");
        System.out.println(course.toJSONString());
    }
 */
