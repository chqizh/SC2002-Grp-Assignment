package Branch;

import Accounts.*;
import Customer.*;

public class Branch {
    private String branchName;
    private String branchLocation;
    private BranchManager branchManagers[];
    private int numManagers;
    private Staff staffList[];
    private int currentNumStaff;
    private int staffQuota;
    private Menu branchMenu;
    private OrderList branchOrders;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String Name) {
        this.branchName = Name;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String Location) {
        this.branchLocation = Location;
    }

    public BranchManager[] getBranchManagers() {
        return branchManagers;
    }


    public int getnumManagers(){
        return numManagers;
    }


    public Staff[] getStaffList() {
        return staffList;
    }

    public void setStaffList(Staff[] staffList) {
        //TODO
        this.staffList = staffList;
        currentNumStaff++;
    }

    public int getCurNumStaff(){
        return currentNumStaff;
    }

    public void setCurNumStaff(int currentNumStaff){
        this.currentNumStaff=currentNumStaff;
    }


    public int getStaffQuota(){
        return staffQuota;
    }

    public void setStaffQuota(int staffQuota){
        this.staffQuota=staffQuota;
    }

    public Menu getBranchMenu() {
        return branchMenu;
    }

    public void setBranchMenu(Menu branchMenu) {
        this.branchMenu = branchMenu;
    }

    public OrderList getBranchOrders() {
        return branchOrders;
    }

    
}
