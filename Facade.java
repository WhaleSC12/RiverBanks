import java.util.ArrayList;
import java.util.UUID;



import java.util.Scanner;
//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User user;
    private static final UserData userData = UserData.getInstance();
    private UserData currenUserData;
    private Course course;
    private Course.Lesson lesson;
    private Course.Lesson.Test test;
    private Course.Lesson.Test.Question question;



    public User Login(String username, String password) 
    {
        for(User u : userData.userData) { 
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) { 
                return u;
            }
        }
        return null;
    }


    public void createLogin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) 
    {
        user = new User(username, password, firstName, lastName, email, phoneNumber, clearance);
        userData.userData.add(user);
    }

    /* search function that searches by name of course
     * can be modified if needed to
     */
    public boolean searchCourse(String courseName) 
    {
        return false;
    }
// will start working over weekend
    public void createLesson(int numLessons) 
    {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter Course title");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course language: ");
        Language language = Language.valueOf(scanner.nextLine().toUpperCase());
        UUID authorUUID = UUID.randomUUID();

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

            Course.Lesson lesson = new Course.Lesson(lessonTitle, lessonDescription, lessonContent, null);
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
        
    }


}
