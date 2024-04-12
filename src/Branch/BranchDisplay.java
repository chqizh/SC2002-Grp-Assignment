package Branch;

public class BranchDisplay {

    private Branch branch;

    public BranchDisplay(Branch branch) {
        this.branch = branch;
    }

    public void displayDetails() {
        System.out.println("Branch Name: " + branch.getBranchName());
        System.out.println("Location: " + branch.getBranchLocation());
        System.out.println("Staff Quota: " + branch.getStaffQuota());
    }
    
}
