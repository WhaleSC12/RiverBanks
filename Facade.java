import java.util.ArrayList;
import java.util.UUID;



import java.util.Scanner;
//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User currentUser;
    private static final UserData userData = UserData.getInstance();
    private UserData currenUserData;
    private Course course;
    private Course.Lesson lesson;
    private Course.Lesson.Test test;
    private Course.Lesson.Test.Question question;

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
    public void createLesson() 
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

            ArrayList<Course.Lesson.Test> tests = new ArrayList<>();

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
                Course.Lesson.Test test = new Course.Lesson.Test(testTitle, testDescription);

                ArrayList<Course.Lesson.Test.Question> questions = new ArrayList<>();
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
                    Course.Lesson.Test.Question question = new Course.Lesson.Test.Question();
                    questions.add(question);
                }

                test.setQuestionList(questions);
                tests.add(test);
            }

            Course.Lesson lesson = new Course.Lesson(lessonTitle, lessonDescription, lessonContent, test);
            lesson.setTest(test);
            course.addLesson(lesson);
        }

        System.out.println("Course created:");
        System.out.println(course.toJSONString());
    }

    /*
     * sets all parameters to null and logs the user out
     * @return null
     */
    public void Logout() {
        currentUser.equals(null);
    }


}
