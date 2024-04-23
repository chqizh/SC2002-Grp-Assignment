package Accounts;
import java.io.Serializable;

import Database.InMemoryDatabase;

/**
* This class represents an employee with a name, staff ID, user type, gender, and age.
*/
public abstract class Employee implements Serializable {
    private String name;
    private String staffID; // New attribute for staff ID
    private UserType userType; // Uses UserType enum
    private char gender;
    private int age;
    protected InMemoryDatabase db;

    /**
     * Constructs a new Employee with the specified details.
     *
     * @param name     the name of the employee
     * @param staffID  the staff ID of the employee
     * @param userType the UserType of the employee
     * @param gender   the gender of the employee, represented as a char ('M' or 'F')
     * @param age      the age of the employee
     */
    public Employee(String name, String staffID, UserType userType, char gender, int age, InMemoryDatabase db) {
        this.name = name;
        this.staffID = staffID; // Initialize staff ID
        this.userType = userType;
        this.gender = gender;
        this.age = age;
        this.db = db;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for staffID
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // Getter and setter for userType
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // Getter and setter for gender
    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    // Getter and setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract String getBranchName();
}
