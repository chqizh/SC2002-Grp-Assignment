package Accounts;

public class Employee extends Account {
    private String name;
    private String role; //Waiter, Dishwasher, Barista etc.
    private char gender;
    private int age;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(char role) {
        ;        
    }

    public char getRole() {
        return role;
    }
}
