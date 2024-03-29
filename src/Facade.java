package src;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


//Where all the method will go that will be used in the src.LearningUI
public class Facade {
    private static User currentUser;
    private static final UserData userData = UserData.getInstance();
    private Course course;
    private Module module;
    private Lesson lesson;
    private static Quiz quiz;
    private Question question;
    static Scanner scanner = new Scanner(System.in);

    /**
     * @param username username used to look for people's data
     * @param password password used to correctly identify the correct src.User
     * @return
     */

    public static User Login(String username, String password) {
        User tmp = userData.getUser(username);
        if (tmp == null) return null;
        if (tmp.getPassword().equals(password))
            return tmp;
        return null;
    }

    /**
     * Takes in the users info and creates a new Login for the user and generates them a unique UUID and then adds them to the UserList
     *
     * @param username    Username the user uses to log in
     * @param password    Password the user uses to log in
     * @param firstName   src.User's first name
     * @param lastName    src.User's last name
     * @param email       src.User's email address
     * @param phoneNumber src.User's phone number
     * @param clearance   src.User's clearance level
     */
    public static void createLogin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        currentUser = new User(username, password, firstName, lastName, email, phoneNumber, clearance);
        userData.userList.add(currentUser);
    }

    /**
     * search function that searches by name of course
     *
     * @param bool returns true if course is there
     */
    public boolean searchCourse(String courseName) {
        return false;
    }


    /**
     * void method that allows Teacher to create src.Lesson
     * asking user to enter name,description,lanuage and amount of lessons for the course and then
     * asks the user how many lessons they want and if they want to add a src.Test to it and write the test with
     * the questions and answer choices
     *
     * @param course   basic course info and holds all lessons within modules
     * @param module   sub-topics within lessons which contain the actual informative content
     * @param Lesson   lessons are groups of modules centered around some topic
     * @param test     basic test info such as name and description and holds all questions
     * @param question basic question info such as question and answers choices and holds correct choice
     */
    
public static Course creatCourse(String title, String description, UUID authorUUID, Language language) {
        Scanner scanner = new Scanner(System.in);

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
                ArrayList<Quiz> quizzes = new ArrayList<>();
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
                        ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();

                        for (int m = 0; m < numChoices; m++) {
                            System.out.println("Enter the text for answer choice " + (m + 1) + ":");
                            String answerString = scanner.nextLine();

                            System.out.println("Is this anser choice correct? (true/false):");
                            boolean isCorrect = scanner.nextBoolean();
                            String tmp = scanner.nextLine();
                            answerList.add(new AbstractMap.SimpleEntry<>(answerString, isCorrect));
                        }
                        question.setAnswerList(answerList);
                        questions.add(question);
                    }

                    Quiz quiz = new Quiz(testName, testDescription);
                    quizzes.add(quiz);
                }

                Lesson l = new Lesson(lessonName, lessonDescription, quiz);
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
    public static void Logout() {
        currentUser.equals(null);
    }

}


