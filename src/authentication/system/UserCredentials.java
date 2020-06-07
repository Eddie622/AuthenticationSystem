package authentication.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserCredentials {
    
    // Private class variables
    private String usernameOnFile, passwordOnFile, userRoleOnFile, line;
    private boolean credentialValidation;
    
    // Converts password input using MD5Digest
    public String MD5Digest(String original) throws NoSuchAlgorithmException {
        
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(original.getBytes());
	byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
	sb.append(String.format("%02x", b & 0xff));
	}
        
        // Returns hashed passwords
        return sb.toString();
    }
    
    // Validates credentials
    public boolean checkCredentials(String username, String password) throws FileNotFoundException {
        
        // File location
        File credentials = new File("src\\authentication\\system\\credentials.txt");
        
        // Scanner to read file
        Scanner scnr = new Scanner(credentials);
        
        // Ensure credentialValidation always begins false
        credentialValidation = false;
        
        // Read document and see if credentials match
        while (scnr.hasNextLine()){
            line = scnr.nextLine();
            
            // Split line into tokens to use for comparison (stored in variables for readability)
            String[] tokens = line.split("\t");
            usernameOnFile = tokens[0];
            passwordOnFile = tokens[1];
            userRoleOnFile = tokens[3];

            if (usernameOnFile.equals(username) && passwordOnFile.equals(password)){
                credentialValidation = true;
                break; // Stop reading txt file if credentials match
            }
        }
        return credentialValidation;        
    }
    
    public void displayUserRole() throws FileNotFoundException {
        
        // File locations
        File admin = new File("src\\authentication\\system\\admin.txt");
        File zookeeper = new File("src\\authentication\\system\\zookeeper.txt");
        File veterinarian = new File("src\\authentication\\system\\veterinarian.txt");
        
        System.out.println();
        
        // Print file according to the userRole
        switch (userRoleOnFile) {
            case "admin":
                {
                    Scanner scnr = new Scanner(admin);
                    while (scnr.hasNextLine()) {
                        System.out.println(scnr.nextLine());
                    }       break;
                }
            case "zookeeper":
                {
                    Scanner scnr = new Scanner(zookeeper);
                    while (scnr.hasNextLine()) {
                        System.out.println(scnr.nextLine());
                    }       break;
                }
            case "veterinarian":
                {
                    Scanner scnr = new Scanner(veterinarian);
                    while (scnr.hasNextLine()) {
                        System.out.println(scnr.nextLine());
                    }       break;
                }
            default:
                System.out.println("\nERROR: NO USER ROLE DETECTED");
                break;
        }
    }
}