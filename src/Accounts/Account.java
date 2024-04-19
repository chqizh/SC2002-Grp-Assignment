package Accounts;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * This class represents an account for a user with a staff ID, password, and role type.
 */
public class Account {
    private String staffID;
    private String passwordHash;

    /**
     * Constructor that initializes an account with a specified staff ID, password, and user type.
     * 
     * @param staffID The staff ID to be set for this account.
     * @param password The password for this account.
     * @param userType The UserType representing the role of the user.
     */
    public Account(String staffID, String password) {
        this.staffID = staffID;
        this.passwordHash = hashPassword(password);
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
            // Ideally, handle this exception in a way that doesn't expose details to the caller.
            throw new RuntimeException("Could not hash password", e);
        }
    }

    /**
     * Validates the login credentials against the stored password hash.
     * 
     * @param staffID The staff ID to validate.
     * @param password The password to validate.
     * @return true if the credentials match, false otherwise.
     */
    public boolean validateLogin(String staffID, String password) {
        return this.passwordHash.equals(hashPassword(password)) && this.staffID.equals(staffID);
    }
}
