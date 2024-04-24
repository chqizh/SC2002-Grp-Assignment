package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;
import Database.*;

/**
 * Represents a manager of a branch with capabilities to manage orders,
 * menus, and staff within the branch.
 */
public class BranchManager extends Employee implements IOrderProcess, IMenuManagement, IStaffManagement {
    private String branchName;
    private transient Scanner sc;

    /**
     * Constructs a BranchManager with specified details and initializes the database.
     * 
     * @param name The name of the branch manager.
     * @param staffID The unique ID of the branch manager.
     * @param gender The gender of the branch manager.
     * @param age The age of the branch manager.
     * @param branchName The name of the branch being managed.
     * @param db The database connection for the branch manager operations.
     */
	public BranchManager(String name, String staffID, char gender, int age, String branchName, InMemoryDatabase db) {
        super(name, staffID, UserType.BRANCH_MANAGER, gender, age, db);
        this.branchName = branchName;
    }

    /**
     * Gets the branch name associated with this branch manager.
     *
     * @return The branch name.
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * Sets the branch name for this branch manager.
     *
     * @param branchName The new branch name.
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * Views new orders for a given branch.
     * 
     * @param branch The branch whose orders are to be viewed.
     */
    public void viewNewOrders(Branch branch) {
        System.out.println("New Orders:");
        branch.getBranchOrders().getOrderList().stream()
                .filter(order -> order.getOrderStatus() == Order.orderStatusFlags.NEW) // Assuming OrderStatus enum
                .forEach(order -> order.printOrder()); // Print each order
    }

    /**
     * Views a specific order by order ID within a branch.
     * 
     * @param branch The branch where the order is located.
     * @param orderID The ID of the order to view.
     */
    public void viewOrder(Branch branch, int orderID) {
        Order order = branch.getBranchOrders().getOrder(orderID);
        if (order != null) {
            order.printOrder();
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }

    /**
     * Processes orders within a branch, allowing status updates such as Processed,
     * Pickup, Completed, or Cancelled.
     * 
     * @param branch The branch where the order is located.
     * @param orderID The ID of the order to process.
     */
    public void processOrders(Branch branch, int orderID) {
        sc = new Scanner(System.in);
        Order order = branch.getBranchOrders().getOrder(orderID);
        if (order != null) {
            System.out.println("Current status is " + order.getOrderStatus());
            System.out.println("Pick update action:");
            System.out.println("(1) Processed");
            System.out.println("(2) Pickup");
            System.out.println("(3) Completed");
            System.out.println("(4) Cancelled");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    order.setOrderStatus(Order.orderStatusFlags.PROCESSED);
                    break;
                case 2:
                    order.setOrderStatus(Order.orderStatusFlags.PICKUP);
                    break;
                case 3:
                    order.setOrderStatus(Order.orderStatusFlags.COMPLETED);
                    break;
                case 4:
                    order.setOrderStatus(Order.orderStatusFlags.CANCELLED);
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
            System.out.println("OrderID " + orderID + " has been updated to " + order.getOrderStatus());
        } else {
            System.out.println("OrderID " + orderID + " not found.");
        }
    }


    /**
     * Views the menu for a given branch.
     * 
     * @param branch The branch whose menu is to be viewed.
     */
    public void viewMenu(Branch branch){
        branch.getBranchMenu().displayMenu();
    }

    /**
     * Adds a menu item to the branch's menu.
     * 
     * @param branch The branch to which the menu item is to be added.
     */
    public void addMenuItem(Branch branch){
        try {
            branch.getBranchMenu().addItems();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Removes a menu item from the branch's menu.
     * 
     * @param branch The branch from which the menu item is to be removed.
     */
    public void removeMenuItem(Branch branch){
        try {
            branch.getBranchMenu().deleteItems();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edits a menu item in the branch's menu.
     * 
     * @param branch The branch whose menu item is to be edited.
     */
    public void editMenuItem(Branch branch){
        System.out.println("What would you like to edit?");
        System.out.println("(1) Update name of menu item.");
        System.out.println("(2) Update price of menu item.");
        System.out.print("Enter your choice: ");
        sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice){
            case 1:
                try {
                    branch.getBranchMenu().updateName();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    branch.getBranchMenu().updatePrice();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid option entered.");
                break;
        }   
    }

    /**
     * Displays a list of staff members within the branch being managed.
     */
	public void displayStaffList(){
        String branch = this.branchName;
        ArrayList<String> managerIDsList = db.getBranchByBranchName(this.branchName).getBranchManagerIDs();
        ArrayList<String> staffIDsList = db.getBranchByBranchName(this.branchName).getStaffIDs();

        System.out.println("Staff List for Branch: " + this.branchName);

        for (String managerID : managerIDsList) {
            BranchManager manager = db.getBranchManager(managerID);
            System.out.println("StaffID: " + manager.getStaffID());
            System.out.println("Name: " + manager.getName());
            System.out.println("Role: " + manager.getUserType().stringFromUserType());
            System.out.println("Age: " + manager.getAge()); // Use getter to retrieve age
            System.out.println("Gender: " + manager.getGender()); // Use getter to retrieve gender
        }        
        for (String staffID : staffIDsList){
            Staff staff = db.getStaff(staffID);
            System.out.println("StaffID: " + staff.getStaffID());
            System.out.println("Name: " + staff.getName());
            System.out.println("Role: " + staff.getUserType().stringFromUserType());
            System.out.println("Age: " + staff.getAge());
            System.out.println("Gender: "+ staff.getGender());
        }
    }
}
