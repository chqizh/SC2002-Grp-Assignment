package Accounts;

public class Manager extends Employee{
    private int branchID;

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchID() {
        return branchID;
    }
}
