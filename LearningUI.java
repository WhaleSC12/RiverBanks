import java.util.Scanner;


public class LearningUI {
private static final String Welcome = "Welcome to the Learning Management System";
private String[] mainMenu = {"Login","Create Account"," Search Course", "Create Course", "Logout"};
private Scanner scanner;
private Facade facade;

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
            login();
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

    private void login() {
    System.out.println("Enter your username");
    System.out.println("Enter q to cancel");
    String usernameinput = scanner.nextLine();
    
    if(input == "q") {
        /*return to main menu */
    } else {
    System.out.println("Enter your password");
    String passwordinput = scanner.nextline();
    }
    /*call facade after getting the login */
    /*set current user to you */
    /*when they log out set current user to null */
    }
    private void createAccount() {
    System.out.println("Create a username");
    System.out.println("Enter q to cancel");
        String newusernameinput = scanner.nextline();
    System.out.println("Create a password");
    String newpasswordinput = scanner.nextline();
    }
    private void createCourse() {
    System.out.println("Enter 1 to add to an existing course. Enter 2 to create a new course.");
    }
    private void existingCourse() {
    System.out.println("Choose a course");
    }
    private void newCourse() {
    System.out.println("Make a title");
    System.out.println("Write a short description");
    System.out.println("What language is the course on?");
    System.out.println("How many questions are there?");

    }
    private void logout() {
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


public void displayMainMenu() {
    System.out.println(mainMenu);
    for(int i=0; i< mainMenu.length; i++) {
        System.out.println((i+1) + ". " + mainMenu[i]);
    }
    
}