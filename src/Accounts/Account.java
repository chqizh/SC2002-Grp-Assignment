package Accounts;

public class Account {
    private String staffID;
    private String password;
    private char userType; // Three types of staff: staff (S), Branch Manager (M), Admin (A). 
    
    Account(){
        this.staffID = null;
        this.password = "password";
        userType = 'S';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffID() {
        return staffID;
    }

    public boolean setUserType(char userType) {
        if (userType == 'S' || userType == 'M' || userType == 'A'){
            this.userType = userType;
            return true;
        }
        else return false;
    }

    public char getUserType() {
        return userType;
    }

    public boolean validateLogin(staffID, password){
        if (password == this.password && staffID = this.staffID) return true;
        else return false;
    }

}
