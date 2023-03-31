import java.util.Scanner;
import java.util.UUID;


public class LearningUI {
private static final String Welcome = "Welcome to the Learning Management System";
private String[] mainMenu = {"Login","Create Account"," Search Course", "Create Course", "Logout"};
private Scanner scanner;
Facade facade = new Facade();
private static User currentUser;
public void Intro() {
    System.out.println(Welcome);
    while(true) {
    displayMainMenu();

            int userChoice = getUserChoice(mainMenu.length);

            if (userChoice == -1) {
                System.out.println("Invalid input");
                continue;
            }
            if (userChoice == mainMenu.length - 1) break;

            switch (userChoice) {
                case (0):
                    loginMethod();
                    break;
                case (1):
                    createAccount();
                    break;
                case (2):
                    createCourse();
                    break;
                case (3):
                    logout();
                    break;

            }
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
   String passwordinput;
   currentUser = Facade.Login(usernameinput, passwordinput);
   
   UUID userUUID = currentUser.getUUID();
    if(usernameinput == "q") {
    displayMainMenu();
    } else {
    System.out.println("Enter your password");
    passwordinput = scanner.nextLine();
    if(usernameinput != null || passwordinput != null) {
    displayMainMenu();
    } else {
        continue;
    }
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
    Facade.createLogin(newusernameinput, newpasswordinput, firstnameinput, lastnameinput, emailinput, phonenumberinput, clearanceinput);

    while(newusernameinput == null || newpasswordinput == null){
    System.out.println("Create a username");
    newusernameinput = scanner.nextLine();
    System.out.println("Enter q to cancel");
    if (newusernameinput == "q") {
        displayMainMenu();
    }
    System.out.println("Create a password");
    newpasswordinput = scanner.nextLine();
    UserData userName = UserData.getInstance();
    if (userName.getUser(newusernameinput) != null){
        System.out.println("Username already taken");
        continue;
    }
    }
    
    while(firstnameinput == null || lastnameinput == null || emailinput == null || phonenumberinput == null || clearanceinput == null){
    System.out.println("Enter your first name");
    firstnameinput = scanner.nextLine();
    System.out.println("Enter your last name");
    lastnameinput = scanner.nextLine();
    System.out.println("Enter your email address");
    emailinput = scanner.nextLine();
    System.out.println("Enter your phone number");
    phonenumberinput = scanner.nextLine();
    System.out.println("Are you a teacher or a student? Enter s for student, Enter t for teacher");
    clearanceinput = scanner.nextLine();
    if (clearanceinput == "s"){
    /*set user to student */
    } else if (clearanceinput == "t"){
    /*set user to teacher */
    } else {
    System.out.println("Invalid input");
    continue;
    }
    }

    displayMainMenu();
    
    }
    private void createCourse() {
    UUID uniqueIdentifier = UUID.randomUUID();
    String moduleName;
    String descriptionInput;
    
    /*Facade.createCourse(uniqueIdentifier, moduleName, descriptionInput, currentUser);*/
    System.out.println("Enter 1 to add to an existing course. Enter 2 to create a new course.");
    }

    private void existingCourse() {
                System.out.println("Choose a course");
    }

    private void newCourse() {
    /*     Facade.createCourse(); */
    }


    private void logout() {
        displayMainMenu();
        /*return to main menu */
        
    }
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
        System.out.println(mainMenu);
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println((i + 1) + ". " + mainMenu[i]);
        }

    }
}