package Branch;

import Accounts.*;
import Customer.*;

public class Branch {
    private int branchID;
    private String branchName;
    private Manager branchManagersList[];
    private int numBranchManagers;
    private Staff staffList[];
    private int numStaff;
    private Menu branchMenu;
    private OrderList branchOrders;

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public void setBranchMenu(Menu branchMenu) {
        this.branchMenu = branchMenu;
    }

    public Menu getBranchMenu() {
        return branchMenu;
    }

    public OrderList getBranchOrders() {
        return branchOrders;
    }
}
