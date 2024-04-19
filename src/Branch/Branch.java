package Branch;

import java.io.Serializable;
import java.util.ArrayList;

import Menu.*;
//import Accounts.*;

public class Branch implements Serializable {
    private String branchName;
    private String branchLocation;
    private ArrayList <String> branchManagerIDs = new ArrayList <String> ();
    private int numManagers;
    private ArrayList <String> staffIDs = new ArrayList <String> ();
    private int currentNumStaff;
    private int staffQuota;
    private ArrayList <MenuItem> branchMenu = new ArrayList <MenuItem> ();
    private OrderList branchOrders;
    private boolean operating;

    public Branch (String branchName, String branchLocation, int staffQuota){
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
    }
    
    public String getBranchName() {
        return branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public ArrayList<String> getBranchManagers() {
        return branchManagerIDs;
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

    public ArrayList<String> getStaffIDs() {
        return staffIDs;
    }

    public int getCurNumStaff(){
        return currentNumStaff;
    }

    public int getStaffQuota(){
        return staffQuota;
    }

    public ArrayList <MenuItem> getBranchMenu() {
        return branchMenu;
    }

    public OrderList getBranchOrders() {
        return branchOrders;
    }

}
