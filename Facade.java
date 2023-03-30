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
        for(User u : userData.userData) { 
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
        userData.userData.add(currentUser);
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
*/
public static void createCouse(String title, String description, UUID authorUUID, Language language) { 
    Scanner scanner = new Scanner(System.in);

    // get course title and description
    System.out.print("Enter course title: ");
    String courseTitle = scanner.nextLine();

    System.out.print("Enter course description: ");
    String courseDescription = scanner.nextLine();

    // create list of modules
    ArrayList<Module> moduleList = new ArrayList<>();

    // create modules
    while (true) {
        // get module title and description
        System.out.print("Enter module title (or 'quit' to finish): ");
        String moduleTitle = scanner.nextLine();

        if (moduleTitle.equalsIgnoreCase("quit")) {
            break;
        }

        System.out.print("Enter module description: ");
        String moduleDescription = scanner.nextLine();

        System.out.print("Enter module content: ");
        String moduleContent = scanner.nextLine();

        // create test
        System.out.print("Enter test title: ");
        String testTitle = scanner.nextLine();

        System.out.print("Enter test description: ");
        String testDescription = scanner.nextLine();

        Test test = new Test(testTitle, testDescription);

        // create questions for test
        while (true) {
            System.out.print("Enter question prompt (or 'quit' to finish): ");
            String questionPrompt = scanner.nextLine();

            if (questionPrompt.equalsIgnoreCase("quit")) {
                break;
            }

            ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();

            System.out.print("Enter number of answer choices: ");
            int numChoices = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            for (int i = 0; i < numChoices; i++) {
                System.out.print("Enter answer choice #" + (i + 1) + ": ");
                String answerText = scanner.nextLine();

                System.out.print("Is this answer choice correct? (true/false): ");
                boolean isCorrect = scanner.nextBoolean();
                scanner.nextLine(); // consume newline character

                answerList.add(new AbstractMap.SimpleEntry<>(answerText, isCorrect));
            }

            Question question = new Question();
            question.setPrompt(questionPrompt);
            question.setAnswerList(answerList);
            test.getQuestionList().add(question);
        }

        Module module = new Module(moduleTitle, moduleDescription,moduleContent);
        module.setTest(test);

        moduleList.add(module);
    }

    // create course
    Module course = new Module(courseTitle, courseDescription,c);
    course.setModuleList(moduleList);

    return course;
}

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
