package Accounts;
import java.io.Serializable;

import Database.InMemoryDatabase;

/**
 * Abstract class representing a generic employee with a name, staff ID, user type, gender, and age.
 * This class provides the structure for employees and must be subclassed to handle specific employee roles.
 */
public abstract class Employee implements Serializable {
    private String name;
    private String staffID;
    private UserType userType;
    private char gender;
    private int age;
    protected InMemoryDatabase db;

    /**
     * Constructs a new Employee with the specified details.
     *
     * @param name     the name of the employee.
     * @param staffID  the staff ID of the employee.
     * @param userType the UserType of the employee.
     * @param gender   the gender of the employee, represented as a char ('M' or 'F').
     * @param age      the age of the employee.
     * @param db       the database instance for the employee to interact with.
     */
    public Employee(String name, String staffID, UserType userType, char gender, int age, InMemoryDatabase db) {
        this.name = name;
        this.staffID = staffID;
        this.userType = userType;
        this.gender = gender;
        this.age = age;
        this.db = db;
    }

    /**
     * Retrieves the name of the employee.
     *
     * @return The current name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the employee.
     *
     * @param name The new name to be set for the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the staff ID of the employee.
     *
     * @return The current staff ID of the employee.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Updates the staff ID of the employee.
     *
     * @param staffID The new staff ID to be set for the employee.
     */
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    /**
     * Retrieves the user type of the employee.
     *
     * @return The current UserType of the employee.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Updates the user type of the employee.
     *
     * @param userType The new UserType to be set for the employee.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Retrieves the gender of the employee.
     *
     * @return The current gender of the employee.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Updates the gender of the employee.
     *
     * @param gender The new gender to be set for the employee.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the age of the employee.
     *
     * @return The current age of the employee.
     */
    public int getAge() {
        return age;
    }

    /**
     * Updates the age of the employee.
     *
     * @param age The new age to be set for the employee.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Abstract method to get the branch name associated with the employee.
     * Implementations of this method should return the name of the branch where the employee works.
     *
     * @return the name of the branch as a String.
     */
    public abstract String getBranchName();
}
