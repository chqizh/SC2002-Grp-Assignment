package Accounts;

import java.io.Console;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Represents an account for a user with a unique staff ID and password. Provides
 * functionalities to manage and authenticate user login credentials.
 */
public class Account implements Serializable {
    private String staffID;
    private String passwordHash;

    /**
     * Initializes an account with a specified staff ID and a default password.
     * 
     * @param staffID The staff ID for this account.
     */
    public Account(String staffID) {
        this.staffID = staffID;
        this.passwordHash = hashPassword("password");
    }

    /**
     * Retrieves the staff ID associated with this account.
     * 
     * @return The staff ID.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Sets the staff ID for this account.
     * 
     * @param staffID The staff ID to be set.
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * Retrieves the hashed password for this account.
     * 
     * @return The hashed password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets a new password after hashing it.
     * 
     * @param password The plain text password to be hashed and set.
     */
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    /**
     * Generates a hashed version of the given password.
     * 
     * @param password The password to hash.
     * @return The hashed password as a Base64 encoded string.
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

    /**
     * Validates the login by comparing the provided password with the stored password hash.
     * 
     * @param password The password to validate.
     * @return true if the password is correct, false otherwise.
     */
    public boolean validateLogin(String password) {
        String hashedInputPassword = hashPassword(password);
        return this.passwordHash.equals(hashedInputPassword);
    }

    /**
     * Checks if the user is using the default password.
     * 
     * @return true if the current password is the default, false otherwise.
     */
    public boolean isUsingDefaultPassword() {
        String defaultPasswordHash = hashPassword("password");
        return this.passwordHash.equals(defaultPasswordHash);
    }

    /**
     * Allows the user to change their password through the console interface.
     * Ensures the new password is not the same as the current password and that
     * the new password is confirmed by re-entry before changing it.
     * 
     * @param console The console interface to interact with the user.
     */
    public void changePassword(Console console) {
        boolean passwordsMatch = false;
        while (!passwordsMatch) {
            char[] newPasswordArray = console.readPassword("New Password: ");
            String newPassword = new String(newPasswordArray);

            if (hashPassword(newPassword).equals(this.passwordHash)) {
                System.out.println("New password cannot be the same as the previous password. Please try again.");
            } else {
                char[] confirmPasswordArray = console.readPassword("Confirm New Password: ");
                String confirmPassword = new String(confirmPasswordArray);

                if (newPassword.equals(confirmPassword)) {
                    setPassword(newPassword);
                    passwordsMatch = true;
                    System.out.println("Password changed successfully.");
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            }
        }
    }
}
