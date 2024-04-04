import Accounts.Manager;
import Accounts.Staff;

public class Branch {
    private String branchName;
    private Manager branchManagers[];
    private Staff staffList[];
    private Orders ordersList[];

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Manager[] getBranchManagers() {
        return branchManagers;
    }

    public void setBranchManagers(Manager[] branchManagers) {
        this.branchManagers = branchManagers;
    }

    public Staff[] getStaffList() {
        return staffList;
    }

    public void setStaffList(Staff[] staffList) {
        this.staffList = staffList;
    }
}
