package Accounts;

import java.io.Console;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * This class represents an account for a user with a staff ID, password, and role type.
 */
public class Account implements Serializable {
    private String staffID;
    private String passwordHash;

    /**
     * Constructor that initializes an account with a specified staff ID, password, and user type.
     * 
     * @param staffID The staff ID to be set for this account.
     * @param password The password for this account.
     * @param userType The UserType representing the role of the user.
     */
    public Account(String staffID){
        this.staffID = staffID;
        this.passwordHash = hashPassword("password");
    }

    /**
     * Gets the staff ID.
     * 
     * @return A string representing the staff ID.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets the staff ID.
     * 
     * @param staffID The staff ID to be set for this account.
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * Gets the hashed password.
     * 
     * @return A string representing the hashed password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the password after hashing it.
     * 
     * @param password The password to be hashed and set.
     */
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    // System.out.println("sussessfully updated to " + this.getPasswordHash());
    }

    /**
     * Hashes a password using SHA-256 and returns the Base64-encoded hash.
     * 
     * @param password The password to hash.
     * @return The Base64 encoded hash of the password.
     */
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password", e);
        }
    }

/*     public boolean validateLogin(String password) {
        String hashedInputPassword = hashPassword(password);
        String defaultPasswordHash = hashPassword("password");
        Console console = System.console();
        System.out.println("current is " + this.getPasswordHash() + "default is "+ defaultPasswordHash); 
        if (this.passwordHash.equals(hashedInputPassword)) {
            while (this.passwordHash.equals(defaultPasswordHash)) {
                System.out.println("You are using the default password. Please change your password.");
                char[] passwordArray = console.readPassword("New Password: "); // Password will be masked
                String newPassword = new String(passwordArray);
                this.setPassword(newPassword);
                }
            return true;
        } else {
            System.out.println("Wrong Password!");
            return false;
        }
    }
}
 */

 public boolean validateLogin(String password) {
    String hashedInputPassword = hashPassword(password);
    if (this.passwordHash.equals(hashedInputPassword)) {
        return true; // Login successful.
    } else {
        System.out.println("Wrong Password!");
        return false; // Login failed.
    }
}

public boolean checkAndChangeDefaultPassword(Console console) {
    String defaultPasswordHash = hashPassword("password");
    if (this.passwordHash.equals(defaultPasswordHash)) {
        System.out.println("You are using the default password. Please change your password.");
        boolean passwordsMatch = false;
        while (!passwordsMatch) {
            char[] newPasswordArray = console.readPassword("New Password: "); // Password will be masked
            char[] confirmPasswordArray = console.readPassword("Confirm New Password: "); // Password will be masked
            
            String newPassword = new String(newPasswordArray);
            String confirmPassword = new String(confirmPasswordArray);
            if (hashPassword(newPassword).equals(this.passwordHash)){
                System.out.println("new password cannot be the same as previous");
            }
            else if (newPassword.equals(confirmPassword)) {
                setPassword(newPassword); // Set new password.
                passwordsMatch = true;
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
            
            // Clear password arrays for security.
            java.util.Arrays.fill(newPasswordArray, ' ');
            java.util.Arrays.fill(confirmPasswordArray, ' ');
        }
        return true; // Password changed.
    }
    return false; // No need to change the password.
    }
}
