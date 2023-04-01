import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class LearningUI {
    public static void main(String[] args) {
        LearningUI runPlease = new LearningUI();
        runPlease.Intro();
    }

    private static final String Welcome = "Welcome to the Learning Management System";
    private final String[] mainMenu = {"Log In", "Sign Up", "Courses", "Create Course", "Show Grades", "Logout"};
    private Scanner scanner;
    Facade facade = new Facade();
    private static User currentUser;

    public void Intro() {
        scanner = new Scanner(System.in);
        System.out.println(Welcome);
        while (true) {
            displayMainMenu();

            int userChoice = getUserChoice(mainMenu.length);

            if (userChoice == -1) {
                System.out.println("Invalid input");
                continue;
            }
            if (userChoice > mainMenu.length - 1) break;

            switch (userChoice) {
                case (0) -> loginMethod();
                case (1) -> createAccount();
                case (2) -> courses();
                case (3) -> createCourse();
                case (4) -> showGrades();
                case (5) -> logout();
            }
        }
    }

    private void showGrades() {
        UserCourseData userCourseData = UserCourseData.getInstance();
        CourseData courseData = CourseData.getInstance();
        HashMap<UUID, UserCourse> currentUserCourseData = userCourseData.courseDataList.get(currentUser.getUUID());
        for (var v : currentUserCourseData.entrySet()) {
            Course course = courseData.getCourse(v.getKey());
            UserCourse userCourse = v.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(course.getTitle());
            sb.append(": \n");
            for (int i = 0; i < userCourse.lessonsCompleted; ++i) {
                Lesson lesson = course.getLessonList().get(i);
                sb.append("\t ").append(lesson.getTitle()).append(": ").append(userCourse.lessonGrades.get(i)).append("\n");
            }
            System.out.println("\n" + sb);
        }
        System.out.println("[1] Return to Main Menu? \n[2] Print Certificate");
        Scanner uin = new Scanner(System.in);
        int userChoice = uin.nextInt();
        switch (userChoice) {
            case (1) -> {
            }// doesn't do anything because right now main menu calls just fall through; else the stack blows
            case (2) -> printCertificate(currentUserCourseData);
            default -> System.out.println("Invalid input. Returning to main menu.");
        }
    }

    private void printCertificate(HashMap<UUID, UserCourse> currentUserCourseData) {
        ArrayList<AbstractMap.SimpleEntry<Course, Double>> passedCourses = new ArrayList<>();

        for (UserCourse userCourse : currentUserCourseData.values()) {
            double totalGrade = 0;
            for (double grade : userCourse.getLessonGrades())
                totalGrade += grade;
            totalGrade = totalGrade / userCourse.lessonsCompleted;
            if (totalGrade >= 70.0)
                passedCourses.add(new AbstractMap.SimpleEntry<>(CourseData.getInstance().getCourse(userCourse.getCourseUUID()), totalGrade));
        }
        System.out.println("Here are your passed courses: \n");
        for (int i = 0; i < passedCourses.size(); ++i) {
            System.out.println("[" + i + "] " + passedCourses.get(i).getKey().getTitle() + " (" + passedCourses.get(i).getValue() + ")\n");
        }
        System.out.println("Enter the corresponding number to the certificate you wish to print: ");
        Scanner uin = new Scanner(System.in);
        int userInput = uin.nextInt();
        try (FileWriter fileWriter = new FileWriter(passedCourses.get(userInput).getKey().getTitle() + ".txt")) {
            fileWriter.write(
                    "//////////////////////////////////////////////////\n" +
                            "You Passed: " + passedCourses.get(userInput).getKey().getTitle() + "\n" +
                            "With Grade: " + passedCourses.get(userInput).getValue() + "\n" +
                            "Congratulations!" +
                            "//////////////////////////////////////////////////\n");
        } catch (IOException ignored) {
            // meh
        }
    }

    /**
     * void login method that asks the users username and passwords
     * and then calls the Login method in the Facade
     *
     * @return null
     */
    public void loginMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username");
        System.out.println("Enter q to cancel");
        String usernameinput = scanner.nextLine();


        if (usernameinput.equals("q")) {
            displayMainMenu();
        } else {
            System.out.println("Enter your password");
            String passwordinput = scanner.nextLine();
            currentUser = Facade.Login(usernameinput, passwordinput);
            UUID userUUID = currentUser.getUUID();


        }





        /*call facade after getting the login */
        /*set current user to you */
        /*when they log out set current user to null */
    }

    /*
     * void createAccount method that asks for info to create an accout such as an
     * username, password, first name, last name, email and phone number
     */
    private void createAccount() {
        String newusernameinput;
        String newpasswordinput;
        String firstnameinput;
        String lastnameinput;
        String emailinput;
        String phonenumberinput;
        String clearanceinput;

        while (true) {
            System.out.println("Create a username");
            newusernameinput = scanner.nextLine();
            System.out.println("Enter q to cancel");
            if (newusernameinput.equals("q")) {
                displayMainMenu();
            }
            System.out.println("Create a password");
            newpasswordinput = scanner.nextLine();
            UserData userName = UserData.getInstance();
            if (userName.getUser(newusernameinput) != null) {
                System.out.println("Username already taken");
                continue;
            }

            System.out.println("Enter your first name");
            firstnameinput = scanner.nextLine();
            System.out.println("Enter your last name");
            lastnameinput = scanner.nextLine();
            System.out.println("Enter your email address");
            emailinput = scanner.nextLine();
            System.out.println("Enter your phone number");
            phonenumberinput = scanner.nextLine();
            System.out.println("Are you a teacher or a student? Enter student for student, Enter teacher for teacher");
            clearanceinput = scanner.nextLine();
            if (clearanceinput.equals("student")) {
                String userClearance = "Student";
            } else if (clearanceinput.equals("teacher")) {
                String userClearance = "Teacher";
            } else {
                System.out.println("Invalid input");
                continue;
            }
            break;
        }
        UserData.getInstance().userList.add(new User(UUID.randomUUID(), newusernameinput, newpasswordinput, firstnameinput, lastnameinput, emailinput, phonenumberinput, clearanceinput));
        DataWriter.saveAll();
    }

    private void createCourse() {
        if (!currentUser.getClearance().equals("Teacher")) {
            System.out.println("Only teachers can create courses");
            return;
        }
        UUID uniqueIdentifier = UUID.randomUUID();

        String title = "Beginner Java lesson";
        String description = "A beginner's guide to early java fundamentals";
        Facade.creatCourse(title, description, uniqueIdentifier, Language.Java);
        System.out.println("Enter 1 to add to an existing course. Enter 2 to create a new course.");
    }

    private void courses() {
        System.out.println("[1] My Courses\n[2] Search for new Course");
        Scanner k = new Scanner(System.in);
        int uint = k.nextInt();
        switch (uint) {
            case (1) -> existingCourse();
            case (2) -> searchCourse();
            default -> System.out.println("Invalid Input. Returning to Main Menu.");
        }
    }

    private void searchCourse() {
        System.out.println("[1] Search by Title");
        System.out.println("[2] Search by Author");
        System.out.println("[3] Search by Language");
        Scanner uin = new Scanner(System.in);
        int input = uin.nextInt();
        switch (input) {
            case (1) -> searchByTitle();
//            case (2) -> searchByAuthor();
//            case (3) -> searchByLanguage();
            // I will do that later if ever
            default -> System.out.println("Invalid input, returning to main menu");
        }
    }

    private void searchByTitle() {
        System.out.println("Enter title or part of title: ");
        Scanner uin = new Scanner(System.in);
        String input = uin.nextLine();
        ArrayList<Course> courseList = CourseData.getInstance().courseList;
        ArrayList<Course> matchingCourses = new ArrayList<>();
        int i = 0;
        for (var v :
                courseList) {
            if (v.getTitle().contains(input)) {
                System.out.println("[" + i + "] " + v.getTitle());
                matchingCourses.add(v);
            }
            ++i;
        }
        System.out.println("Pick a Course");
        int u = uin.nextInt();
        UserCourseData userCourseData = UserCourseData.getInstance();
        var v = userCourseData.courseDataList;
        var dat = v.get(currentUser.getUUID());
        UserCourse userCourse = new UserCourse(currentUser.getUUID(), matchingCourses.get(u).getUUID(), 0, new ArrayList<>());
        dat.putIfAbsent(matchingCourses.get(u).getUUID(), userCourse);
        System.out.println("Course added to My Courses. Returning to Main Menu.");
    }


    private void existingCourse() {
        UUID userUUID = currentUser.getUUID();
        System.out.println("Choose a course");
        UserCourseData courseInfo = UserCourseData.getInstance();
        CourseData courseData = CourseData.getInstance();
        HashMap<UUID, UserCourse> coursePrint = courseInfo.courseDataList.get(userUUID);
        for (var v : coursePrint.entrySet()) {
            UUID courseKey = v.getKey();
            Course someCourse = courseData.getCourse(courseKey);
            
            System.out.println(someCourse.getTitle());
            ArrayList<Lesson> lessonList = someCourse.getLessonList();
            int i = 0;
            for (var z : v.getValue().getLessonGrades()) {
            System.out.println(lessonList.get(i).getTitle());
            System.out.println(z); 
            ++i;
            }
        }
        System.out.println("Choose which course to access");
        String somecourse = scanner.nextLine();
        Course someName = courseData.getCourse(somecourse);
        
        if (currentUser.getClearance().equals("Teacher")) {
            System.out.println("Add Lesson? [y/n]");
            String userinput = new Scanner(System.in).nextLine();
            if (userinput.equals("y")) {
                System.out.println("Title?");
                String title = new Scanner(System.in).nextLine();
                System.out.println("Description?");
                String description = new Scanner(System.in).nextLine();
                Test test = new Test("null", "null");
                someName.addLesson(new Lesson(title, description, test));
            }
        }

        for (int i = 0; i < someName.getLessonList().size(); i++) {
        System.out.println(someName.getLessonList().get(i).getTitle());
        
        }
        System.out.println("Choose which lesson to access");
        int someLesson = scanner.nextInt();
        Lesson lesson = someName.getLessonList().get(someLesson);
            
        System.out.println("Enter c to access comments, otherwise enter anything else");
        scanner.nextLine();
        String commentChoice = scanner.nextLine(); 
        if (commentChoice.equals("c")) {
            ArrayList<Comment> clist = lesson.getCommentList();
        while (true){
            System.out.println("Enter your comment or enter exit to exit");
            String commentInput = scanner.nextLine();
            if (commentInput.equals("exit")) {
            System.exit(0);
            } else {
            Comment usercomment = new Comment(userUUID, commentInput);
            clist.add(usercomment);
            for (int i = 0; i < clist.size(); i++){
            System.out.println(UserData.getInstance().getUser(clist.get(i).getAuthor()).getUsername());
            System.out.println(clist.get(i).getContent());
            DataWriter.saveAll();
            }
            }
        }
        }
       
        if (currentUser.getClearance().equals("Teacher")) {
            System.out.println("Add Module? [y/n]");
            String userinput = new Scanner(System.in).nextLine();
            if (userinput.equals("y")) {
                System.out.println("Title?");
                String title = new Scanner(System.in).nextLine();
                System.out.println("Description?");
                String description = new Scanner(System.in).nextLine();
                System.out.println("Lesson Content?");
                String content = new Scanner(System.in).nextLine();
                lesson.getModuleList().add(new Module(title, description, content));
        }
            System.out.println("Modify Test? [y/n]");
            userinput = new Scanner(System.in).nextLine();
            if (userinput.equals("y")) {
                Test test = lesson.getTest();
                System.out.println(test.getTitle());
                System.out.println(test.getDescription());
                for (Question q :
                        test.getQuestionList()) {
                    System.out.println(q.getPrompt());
                    for (int i = 0; i < q.getAnswerList().size(); i++) {
                        System.out.println("\t" + i + 1 + "): " + q.getAnswerList().get(i).getKey() + "\t | " + q.getAnswerList().get(i).getValue().toString());
                    }
                    System.out.println("\n");
                }
                System.out.println("Add Question? [y/n]");
                if (new Scanner(System.in).nextLine().equals("y")) {
                    Question question = new Question();
                    System.out.println("Prompt: ");
                    String prompt = new Scanner(System.in).nextLine();
                    question.setPrompt(prompt);
                    question.setAnswerList(new ArrayList<>());
                    System.out.println("How many answers?");
                    int num = new Scanner(System.in).nextInt();
                    for (int i = 0; i < num; i++) {
                        System.out.println("Answer " + i + 1 + " Text: ");
                        String answer1 = new Scanner(System.in).nextLine();
                        System.out.println("Answer " + i + 1 + " Correct? [true/false]");
                        boolean correct = new Scanner(System.in).nextBoolean();
                        question.getAnswerList().add(new AbstractMap.SimpleEntry<>(answer1, correct));
                    }
                    System.out.println("Question Added");
                }
                System.out.println("Test Modified");
            }
        }

        for (int i = 0; i < lesson.getModuleList().size(); i++) {
            
            System.out.println(lesson.getModuleList().get(i).getTitle());
        }
        System.out.println("Choose which module to access");
        int UserInput = scanner.nextInt();
        Module someQuestion = lesson.getModuleList().get(UserInput);
        System.out.println(someQuestion.getContent());
        System.out.println("Would you like to view the textbook? Enter 0 to view it.");
        int UserTextbookInput = scanner.nextInt();
        if (UserTextbookInput == 0){
        ToTextFile.write(someQuestion);
        }
    }


    private void logout() {
        currentUser = null;
    }


    private int getUserChoice(int numCommands) {
        System.out.print("Enter a number: ");

        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if (command >= 0 && command <= numCommands - 1) return command;

        return -1;
    }

    /**
     * void methods that displays the main menu options in the UI
     *
     * @return null
     */
    public void displayMainMenu() {
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println((i + 1) + ". " + mainMenu[i]);
        }

    }
}
