import java.util.Scanner;
import java.util.UUID;


public class LearningUI {
private static final String Welcome = "Welcome to the Learning Management System";
private String[] mainMenu = {"Login","Create Account"," Search Course", "Create Course", "Logout"};
private Scanner scanner;
Facade facade = new Facade();
private static User currentUser;
currentUser.getUUID();
public void Intro() {
    System.out.println(Welcome);
    while(true) {
    displayMainMenu();

            int userChoice = getUserChoice(mainMenu.length);
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
    public static void loginMethod() {
    System.out.println("Enter your username");
    System.out.println("Enter q to cancel");
   String usernameinput = scanner.nextLine();
   String passwordinput;
   Facade.Login(usernameinput, passwordinput);

    if(usernameinput == "q") {
        /*return to main menu */
    } else {
    System.out.println("Enter your password");
    passwordinput = scanner.nextline(); 
    }
    

    /*call facade after getting the login */
    /*set current user to you */
    /*when they log out set current user to null */
    }

    /**
     * void createAccount method that asks for info to create an accout such as an
     * username, password, first name, last name, email and phone number
     *
     * @return null
     */
    /**
     * void createAccount method that asks for info to create an accout such as an
     * username, password, first name, last name, email and phone number
     *
     * @return null
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
    System.out.println("Create a username");
    newusernameinput = scanner.nextLine();
    System.out.println("Enter q to cancel");
    System.out.println("Create a password");
    newpasswordinput = scanner.nextLine();
    }

    private void createCourse() {
    UUID uniqueIdentifier = UUID.randomUUID();
    String moduleName;
    String descriptionInput;
    
    Facade.createCourse(uniqueIdentifier, moduleName, descriptionInput, currentUser, );
    System.out.println("Enter 1 to add to an existing course. Enter 2 to create a new course.");
    }

    private void existingCourse() {
        Facade.
                System.out.println("Choose a course");
    }

    private void newCourse() {
        Facade.createCourse();
        Facade.createCourse();
    }


    private void logout() {
        Facade.logout();
        /*return to main menu */
        Facade.logout();
        /*return to main menu */
    }
}



    private int getUserChoice(int numCommands) {
        System.out.print("Enter a number: ");
    private int getUserChoice(int numCommands) {
        System.out.print("Enter a number: ");

        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;
        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if (command >= 0 && command <= numCommands - 1) return command;

        return -1;
    }
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