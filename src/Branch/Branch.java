package Branch;

import Accounts.*;
import Customer.*;

public class Branch {
    private int branchID;
    private String branchName;
    private Manager branchManagers[];
    private int numManagers;
    private Staff staffList[];
    private int currentNumStaff;
    private int staffQuota;
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

    public void setBranchManagers(Manager[] branchManagers,int numManagers) {
        //TODO



    }

    public int getnumManagers(){
        return numManagers;
    }

    public void setnumManagers(int numStaff){
        if (numStaff>=1 && numStaff<=4){
            this.numManagers=1;
        }
        else if (numStaff>=5 && numStaff<=8){
            this.numManagers=2;
        }
        else if (numStaff>=9 && numStaff<=15){
            this.numManagers=3;
        }
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

    public Staff findStaff(String staffID){
        return ;
    }
}
