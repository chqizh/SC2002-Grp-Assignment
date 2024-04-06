package Accounts;

public class Staff extends Employee{
    private String branchID;

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }
    
    public String getBranchID() {
        return branchID;
    }
}
