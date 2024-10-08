package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;
import Database.*;

/**
 * Represents a manager of a branch with capabilities to manage orders,
 * menus, and staff within the branch.
 * Inherits from Employee and implements interfaces for order, menu and staff management.
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

            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            }
            catch (Exception e){
                sc.nextLine();
            }
            
            boolean updated = false;
            switch (choice) {
                case 1:
                    order.setOrderStatus(Order.orderStatusFlags.PROCESSED);
                    updated = true;
                    break;
                case 2:
                    order.setOrderStatus(Order.orderStatusFlags.PICKUP);
                    updated = true;
                    break;
                case 3:
                    order.setOrderStatus(Order.orderStatusFlags.COMPLETED);
                    updated = true;
                    break;
                case 4:
                    order.setOrderStatus(Order.orderStatusFlags.CANCELLED);
                    updated = true;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
            if (updated) System.out.println("OrderID " + orderID + " has been updated to " + order.getOrderStatus());
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
        branch.getBranchMenu().displayMenu(branch);
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
        int choice = 0;
        try {
            choice = sc.nextInt();
            sc.nextLine();
        }
        catch (Exception e) {
            sc.nextLine();
        }
        
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
     * @param displayAll A true or false value to display all staff without filtering or not.
     */
	public void displayStaffList(boolean displayAll){
        sc = new Scanner(System.in);
        
        ArrayList<String> employeesIDsList = new ArrayList<>();
        employeesIDsList.addAll(db.getBranchByBranchName(this.branchName).getBranchManagerIDs());
        employeesIDsList.addAll(db.getBranchByBranchName(this.branchName).getStaffIDs()); 

        ArrayList<Employee> employeesList = new ArrayList<>();
        for (String employeeID : employeesIDsList) {
            Employee employee = db.getEmployee(employeeID);
            if (employee != null) employeesList.add(employee);
        }

        System.out.println("Displaying Staff List for Branch: " + this.branchName);
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
        System.out.println("----------------------------------------------------------------------");
        for (Employee employee : employeesList){
            System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.print("Press any key to continue.");
        sc.nextLine();
    }
}
