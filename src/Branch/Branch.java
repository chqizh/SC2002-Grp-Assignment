package Branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;
import Customer.*;

public class Branch implements Serializable {
    private String branchName;
    private String branchLocation;
    private ArrayList <String> branchManagerIDs = new ArrayList <String> ();
    private int maxNumManagers;
    private ArrayList <String> staffIDs = new ArrayList <String> ();
    private int currentNumStaff;
    private int staffQuota;
    private MenuItems branchMenu = new MenuItems();
    private OrderList branchOrders = new OrderList();
    private transient Scanner sc;
    
    public Branch (String branchName, String branchLocation, int staffQuota){
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.maxNumManagers = 1;
        this.staffQuota = staffQuota;
        this.sc = new Scanner(System.in);
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchLocation(String branchLocation){
        this.branchLocation = branchLocation;
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
        else if (branchManagerIDs.size() >= maxNumManagers){
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
            String newBranchManager = sc.next();
            addBranchManager(newBranchManager);
            return true;
        }
        else {
            return false;
        }
    }

    public void setMaxNumManagers(int currentNumStaff){
        if (currentNumStaff>=1 && currentNumStaff<=4){
            this.maxNumManagers=1;
        }

        else if (currentNumStaff>=5 && currentNumStaff<=8){
            this.maxNumManagers=2;
        }

        else if (currentNumStaff>=9 && currentNumStaff<=15){
            this.maxNumManagers=4;
        }
    }

    public int getnumManagers(){
        return maxNumManagers;
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
            currentNumStaff++;
            setMaxNumManagers(currentNumStaff);
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
            currentNumStaff--;
            setMaxNumManagers(currentNumStaff);
            return true;
        }
    }

    public int getCurNumStaff(){
        return currentNumStaff;
    }

    public void setStaffQuota(int staffQuota){
        this.staffQuota = staffQuota;
    }

    public int getStaffQuota(){
        return staffQuota;
    }

    public MenuItems getBranchMenu() {
        return branchMenu;
    }

    public OrderList getBranchOrders() {
        return branchOrders;
    }

    public void addOrder(Order order){
        this.branchOrders.addOrder(order);
    }

    public void removeOrder(int orderID){
        this.branchOrders.removeOrder(orderID);
    }

    public int nextOrderID(){
        int maxOrderID = branchOrders.getOrderList().size();
        return maxOrderID++;
    }
}
