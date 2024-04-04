package Accounts;

import javax.swing.plaf.TreeUI;

public class Account {
    private String username;
    private String password;

    Account(){
        this.password = "password";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword){
        if (inputPassword == this.password) return true;
        else return false;
    }
}
