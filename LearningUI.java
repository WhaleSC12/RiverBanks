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

    }
    private void createAccount() {

    }
    private void createCourse() {
        
    }
    private void logout() {

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