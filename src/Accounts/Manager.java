package Accounts;

public class Manager extends Staff{
    private String branchName;

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }
}
