package Branch;

import java.io.Serializable;
import java.util.ArrayList;

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
    private Menu branchMenu;
    private OrderList branchOrders;

    /**
     * Constructs a branch with a specific name, location, and staff quota.
     *
     * @param branchName     The name of the branch.
     * @param branchLocation The location of the branch.
     * @param staffQuota     The maximum number of staff allowed in this branch.
     */
    public Branch(String branchName, String branchLocation, int staffQuota){
        this.branchManagerIDs = new ArrayList<>();
        this.maxNumManagers = 1;
        this.staffIDs = new ArrayList<>();
        this.currentNumStaff = 0;
        this.branchMenu = new Menu(branchName);
        this.branchOrders = new OrderList();
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

    /**
     * Sets the location of the branch.
     *
     * @param branchLocation The new location of the branch.
     */
    public void setBranchLocation(String branchLocation){
        this.branchLocation = branchLocation;
    }

    /**
     * Gets the location of the branch.
     *
     * @return The location of the branch.
     */
    public String getBranchLocation() {
        return branchLocation;
    }

    /**
     * Gets a list of branch manager IDs.
     *
     * @return An ArrayList of Strings containing the IDs of branch managers.
     */
    public ArrayList<String> getBranchManagerIDs() {
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
            System.out.printf("Branch already contains Branch Manager with staffID %s. \n", staffID);
            return false;            
        }
        else if (branchManagerIDs.size() >= maxNumManagers){
            System.out.printf("Branch already maximum number of Branch Managers and %s could not be added. \n", staffID);
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
            System.out.println("Number of Branch Managers is below required amount. Please add another Branch Manager.");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the maximum number of managers for the branch based on the current number of staff.
     *
     * @param currentNumStaff The current number of staff members in the branch.
     */
    public void setMaxNumManagers(){
        if (this.currentNumStaff >= 1 && this.currentNumStaff <= 4){
            this.maxNumManagers = 1;
        }
        else if (this.currentNumStaff >= 5 && this.currentNumStaff <= 8){
            this.maxNumManagers = 2;
        }
        else if (this.currentNumStaff >= 9 && this.currentNumStaff <= 15){
            this.maxNumManagers = 3;
        }
    }
    /**
     * Gets the number of managers in the branch.
     *
     * @return The maximum number of managers allowed in the branch.
     */
    public int getnumManagers(){
        return maxNumManagers;
    }

    /**
     * Gets a list of staff IDs for the branch.
     *
     * @return An ArrayList of Strings containing the IDs of staff members.
     */
    public ArrayList<String> getStaffIDs() {
        return staffIDs;
    }

    /**
     * Adds a staff member to the branch if the staff quota has not been reached
     * and the staff ID is not already present.
     *
     * @param staffID The ID of the staff member to be added to the branch.
     * @return True if the staff member was added successfully, false otherwise.
     */
    public boolean addStaff(String staffID){
        if (staffIDs.contains(staffID)){
            System.out.printf("Branch already contains staff with staffID %s.\n", staffID);
            return false;
        }
        else if (staffIDs.size() >= staffQuota){
            System.out.println("Staff quota is already filled.");
            return false;
        }
        else {
            staffIDs.add(staffID);
            currentNumStaff++;
            setMaxNumManagers();
            return true;
        }
    }

    /**
     * Removes a staff member from the branch if the staff ID is present.
     *
     * @param staffID The ID of the staff member to be removed from the branch.
     * @return True if the staff member was removed successfully, false otherwise.
     */
    public boolean removeStaff(String staffID){
        if (!staffIDs.contains(staffID)){
            System.out.printf("Branch does not contain Staff with staffID %s", staffID);
            return false;
        }
        else {
            staffIDs.remove(staffID);
            currentNumStaff--;
            setMaxNumManagers();
            return true;
        }
    }

    /**
     * Gets the current number of staff members in the branch.
     *
     * @return The current number of staff members.
     */
    public int getCurNumStaff(){
        return currentNumStaff;
    }
    
    /**
     * Sets the staff quota for the branch.
     *
     * @param staffQuota The new staff quota.
     */
    public void setStaffQuota(int staffQuota){
        this.staffQuota = staffQuota;
    }

    /**
     * Gets the staff quota for the branch.
     *
     * @return The staff quota.
     */
    public int getStaffQuota(){
        return staffQuota;
    }
    
    /**
     * Gets the menu of the branch.
     *
     * @return The Menu object for the branch.
     */
    public Menu getBranchMenu() {
        return branchMenu;
    }

    /**
     * Gets the orders list of the branch.
     *
     * @return The OrderList object containing the orders for the branch.
     */
    public OrderList getBranchOrders() {
        return branchOrders;
    }

    /**
     * Adds an order to the branch's list of orders.
     *
     * @param order The order to be added to the list.
     */
    public void addOrder(Order order){
        this.branchOrders.addOrder(order);
    }

    /**
     * Removes an order from the branch's list of orders by order ID.
     *
     * @param orderID The ID of the order to be removed.
     */
    public void removeOrder(int orderID){
        this.branchOrders.removeOrder(orderID);
    }
}
