package Accounts;

public class Staff extends Account {
    private String name;
    private char role;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(char role) {
        // Three types of staff: staff (S), Branch Manager (M), Admin (A). 
        if (role == 'S' || role == 'M' || role == 'A') this.role = role;
    }

    public char getRole() {
        return role;
    }
}
