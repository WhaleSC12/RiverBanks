import java.util.Scanner;


public class LearningUI {
private static final String Welcome = "Welcome to the Learning Management System";
private String[] mainMenu = {"Login","Create Account"," Search Course", "Create Course", "Logout"};
private Scanner scanner;
private Facade facade;
Facade facade = new Facade();

public void Intro() {
    System.out.println(Welcome);
    while(true) {
    displayMainMenu();

    int userChoice = getUserChoice(mainMenu.length);

    if(userChoice == -1) {
        System.out.println("Invalid input");
        continue;
    }
    if(userChoice == mainMenu.length -1) break;
    
    switch(userChoice) {
        case(0):
            loginMethod();
            break;
        case(1):
            createAccount();
            break;
        case(2):
            createCourse();
            break;
        case(3):
            logout();
            break;

    }
    }
/** void login method that asks the users username and passwords
 * and then calls the Login method in the Facade
 * @return null
 */
    public static void loginMethod() {
    System.out.println("Enter your username");
    System.out.println("Enter q to cancel");
    Facade.Login();
String usernameinput = scanner.nextLine();

    if(usernameinput == "q") {
        /*return to main menu */
    } else {
    System.out.println("Enter your password");
    String passwordinput = scanner.nextLine();
    }
    

    /*call facade after getting the login */
    /*set current user to you */
    /*when they log out set current user to null */
    }

/** void createAccount method that asks for info to create an accout such as an
 * username, password, first name, last name, email and phone number
 * @return null
 */
    private void createAccount() {
    Facade.createLogin();
    System.out.println("Create a username");
    System.out.println("Enter q to cancel");
        String newusernameinput = scanner.nextLine();
    System.out.println("Create a password");
    String newpasswordinput = scanner.nextLine();
    }

    private void createCourse() {
    Facade.creatCourse();
    System.out.println("Enter 1 to add to an existing course. Enter 2 to create a new course.");
    }

    private void existingCourse() {
    Facade.
    System.out.println("Choose a course");
    }

    private void newCourse() {
    Facade.createCourse();
    }
    private void logout() {
    Facade.logout();
    /*return to main menu */
    }
}

private int getUserChoice(int numCommands) {
    System.out.print("Enter a number: ");

    String input = scanner.nextLine();
    int command = Integer.parseInt(input) - 1;

    if(command >= 0 && command <= numCommands -1) return command;

    return -1;
}

/** void methods that displays the main menu options in the UI
 * @return null
 */
public void displayMainMenu() {
    System.out.println(mainMenu);
    for(int i=0; i< mainMenu.length; i++) {
        System.out.println((i+1) + ". " + mainMenu[i]);
    }

}