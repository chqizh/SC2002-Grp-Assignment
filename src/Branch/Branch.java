package Branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;
import Customer.*;

/**
 * Represents a branch of the fast food ordering and management system.
 * This class encapsulates all the necessary attributes and methods 
 * to manage a branch, including its staff, managers, menu, and orders.
 */
public class Branch implements Serializable {
    private String branchName;
    private String branchLocation;
    private ArrayList <String> branchManagerIDs;
    private int maxNumManagers;
    private ArrayList <String> staffIDs;
    private int currentNumStaff;
    private int staffQuota;
    private MenuItems branchMenu;
    private OrderList branchOrders;
    private int maxOrderID;
    private transient Scanner sc;

    /**
     * Default constructor for creating a branch with no initial setup.
     */
    public Branch(){
        this.branchManagerIDs = new ArrayList<>();
        this.maxNumManagers = 1;
        this.staffIDs = new ArrayList<>();
        this.currentNumStaff = 0;
        this.branchMenu = new MenuItems();
        this.branchOrders = new OrderList();
        this.maxOrderID = 0;
        this.sc = new Scanner(System.in);
    }
    
    /**
     * Constructs a branch with a specific name, location, and staff quota.
     *
     * @param branchName     The name of the branch.
     * @param branchLocation The location of the branch.
     * @param staffQuota     The maximum number of staff allowed in this branch.
     */
    public Branch (String branchName, String branchLocation, int staffQuota){
        this.branchManagerIDs = new ArrayList<>();
        this.maxNumManagers = 1;
        this.staffIDs = new ArrayList<>();
        this.currentNumStaff = 0;
        this.branchMenu = new MenuItems();
        this.branchOrders = new OrderList();
        this.maxOrderID = 0;
        this.sc = new Scanner(System.in);
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;   
    }

    /**
     * Gets the name of the branch.
     *
     * @return The name of the branch.
     */
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

    /**
     * Adds a new branch manager to the branch if the maximum number
     * of managers has not been reached and the manager is not already present.
     *
     * @param staffID The ID of the branch manager to be added to branch.
     * @return True if the manager was added successfully, false otherwise.
     */
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

    /**
     * Removes a branch manager to the branch manager is not present in Branch.
     * Prompts user to add back a manager to hit manager quota.
     * 
     * @param staffID The ID of the branch manager to be removed from branch.
     * @return True if the manager was removed successfully, false otherwise.
     */
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
        return ++maxOrderID;
    }
}
