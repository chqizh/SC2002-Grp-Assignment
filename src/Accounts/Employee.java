package Accounts;

/**
 * This class represents an employee with a name, user type, gender, and age.
 */
public class Employee {
    private String name;
    private UserType userType; // Uses UserType enum
    private char gender;
    private int age;

    /**
     * Constructs a new Employee with the specified details.
     *
     * @param name     the name of the employee
     * @param userType the UserType of the employee
     * @param gender   the gender of the employee, represented as a char ('M' or 'F')
     * @param age      the age of the employee
     */
    public Employee(String name, UserType userType, char gender, int age) {
        this.name = name;
        this.userType = userType;
        this.gender = gender;
        this.age = age;
    }
    
    /**
     * Gets the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name The new name of the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user type of the employee.
     *
     * @return The user type of the employee.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the user type of the employee.
     *
     * @param userType The new user type of the employee.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Gets the gender of the employee.
     *
     * @return The gender of the employee.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender The new gender of the employee.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Gets the age of the employee.
     *
     * @return The age of the employee.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the employee.
     *
     * @param age The new age of the employee.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
