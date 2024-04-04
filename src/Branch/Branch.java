package Branch;

import Accounts.*;

public class Branch {
    private String branchLocation;
    private Manager branchManagers[];
    private int numManagers;
    private Staff staffList[];
    private int numStaff;
    private Orders ordersList[];

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchName) {
        this.branchLocation = branchName;
    }

    public Manager[] getBranchManagers() {
        return branchManagers;
    }

    public void setBranchManagers(Manager[] branchManagers) {
        //TODO
        this.branchManagers = branchManagers;
        numManagers++;
    }

    public Staff[] getStaffList() {
        return staffList;
    }

    public void setStaffList(Staff[] staffList) {
        //TODO
        this.staffList = staffList;
        numStaff++;
    }
}
