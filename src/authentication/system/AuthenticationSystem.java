package authentication.system;

/**
 *
 * @author Heriberto Torres
 */

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AuthenticationSystem {

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        
        // Scanner for user input
        Scanner scnr = new Scanner(System.in);
        
        // Used to check credentials
        UserCredentials userInput = new UserCredentials();
        
        // Local variables
        String username, password, loggedInInput;
        int loginAttempts = 3;
        boolean login = false;
        boolean programEnd = false;
        
        // Main menu
        System.out.println("Welcome to the Zoo Computer System.\n\n"
                + "Please enter username and password or q to quit.");
        
        while (!programEnd){
 
            // Collect username
            System.out.print("\nUsername: ");
            username = scnr.nextLine();
            if (username.equals("q")){
                break; // End program if user enters "q" to quit
            }
            
            // Collect and convert password
            System.out.print("Password: ");
            password = scnr.nextLine();
            password = userInput.MD5Digest(password);

            // Check credentials
            login = userInput.checkCredentials(username, password);
            
            // Successful login
            if (login) {
                loginAttempts = 3; // Replenish login attempts after successful login
                userInput.displayUserRole(); // Display userRole screen
                while (login){ // Stay logged in until user logs out
                    System.out.println("\nEnter q to logout");
                    loggedInInput = scnr.nextLine();
                    if (loggedInInput.equals("q")){
                        login = false;
                    }
                }                
            }
            // Unsuccessful login
            else {
                loginAttempts--;
                System.out.println("\nLog in failed " + loginAttempts + " login attempts remaining.");
            }
            
            // End program if there are no more loginAttempts remaining
            if (loginAttempts <= 0){
                System.out.println("\nYou have failed to log in 3 times. Exiting program...");
                programEnd = true;
            }
            
        }
    }      
}

