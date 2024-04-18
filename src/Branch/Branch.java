package Branch;

import Accounts.*;

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

    Branch (String branchName, String branchLocation, int staffQuota, Staff staffList[]){
        this.branchName=branchName;
        this.branchLocation=branchLocation;
        this.staffQuota=staffQuota;
        this.staffList=staffList;
        this.currentNumStaff=staffList.length;
        setnumManagers(currentNumStaff);
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public BranchManager[] getBranchManagers() {
        return branchManagers;
    }

    public void setnumManagers(int currentNumStaff){
        if (currentNumStaff>=1 && currentNumStaff<=4){
            this.numManagers=1;
        }

        else if (currentNumStaff>=5 && currentNumStaff<=8){
            this.numManagers=2;
        }

        else if (currentNumStaff>=9 && currentNumStaff<=15){
            this.numManagers=4;
        }
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
