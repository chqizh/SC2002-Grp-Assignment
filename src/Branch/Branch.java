package Branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;
//import Accounts.*;

public class Branch implements Serializable {
    private int branchID;
    private static int nextBranchID=1;
    private String branchName;
    private String branchLocation;
    private ArrayList <String> branchManagerIDs = new ArrayList <String> ();
    private int numManagers = 1;
    private ArrayList <String> staffIDs = new ArrayList <String> ();
    private int currentNumStaff;
    private int staffQuota;
    private ArrayList <MenuItem> branchMenu = new ArrayList <MenuItem> ();
    private OrderList branchOrders;
    
    public Branch (String branchName, String branchLocation, int staffQuota){
        this.branchID = nextBranchID;
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
        nextBranchID++;
    }

    public int getbranchID(){
        return branchID;
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

    public boolean addBranchManager(String staffID){
        if (branchManagerIDs.contains(staffID)){
            System.out.printf("Branch already contains Branch Manager with staffID %s.", staffID);
            return false;            
        }
        else if (branchManagerIDs.size() >= numManagers){
            System.out.printf("Branch already maximum number of Branch Managers.", staffID);
            return false;
        }
        else {
            branchManagerIDs.add(staffID);
            return true;
        }
    }

    public boolean removeBranchManager(String staffID){
        if (branchManagerIDs.contains(staffID)){
            System.out.printf("Branch Manager with staffID %s successfully removed.", staffID);
            branchManagerIDs.remove(staffID);
            System.out.printf("Number of Branch Managers is below required amount. Please add another Branch Manager.", staffID);
            Scanner sc = new Scanner(System.in);
            String newBranchManager = sc.next();
            addBranchManager(newBranchManager);
            return true;            
        }
        else {
            return false;
        }
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

    public boolean addStaff(String staffID){
        if (staffIDs.contains(staffID)){
            System.out.printf("Branch already contains Staff with staffID %s", staffID);
            return false;
        }
        else if (staffIDs.size() >= staffQuota){
            System.out.println("Staff quota is already filled.");
            return false;
        }
        else {
            staffIDs.add(staffID);
            return true;
        }
    }

    public boolean removeStaff(String staffID){
        if (!staffIDs.contains(staffID)){
            System.out.printf("Branch does not contain Staff with staffID %s", staffID);
            return false;
        }
        else {
            staffIDs.remove(staffID);
            return true;
        }
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
