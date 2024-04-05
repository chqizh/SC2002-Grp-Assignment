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

    public void setBranchManagers(Manager[] branchManagers,int numStaff) {
        //TODO
        int noOfManagers=0;

        if (numStaff>=1 && numStaff<=4){
            noOfManagers=1;
        }
        else if (numStaff>=5 && numStaff<=8){
            noOfManagers=2;
        }
        else if (numStaff>=9 && numStaff<=15){
            noOfManagers=3;
        }
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
