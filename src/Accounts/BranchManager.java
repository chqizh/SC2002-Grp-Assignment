package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;
import Database.*;

public class BranchManager extends Employee implements IOrderProcess, IMenuManagement, IStaffManagement {
    private String branchName;
    private transient Scanner sc;

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

    // From IOrderProcess
    public void viewNewOrders(Branch branch) {
        System.out.println("New Orders:");
        branch.getBranchOrders().getOrderList().stream()
                .filter(order -> order.getOrderStatus() == Order.orderStatusFlags.NEW) // Assuming OrderStatus enum
                .forEach(order -> order.printOrder()); // Print each order
    }

    public void viewOrder(Branch branch, int orderID) {
        Order order = branch.getBranchOrders().getOrder(orderID);
        if (order != null) {
            order.printOrder();
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }

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


// From IMenuManagement
    public void viewMenu(Branch branch){
        branch.getBranchMenu().displayMenu();
    }

    public void addMenuItem(Branch branch){
        try {
            branch.getBranchMenu().addItems();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeMenuItem(Branch branch){
        try {
            branch.getBranchMenu().deleteItems();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

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

// From IStaffManagement
	public void displayStaffList(){
        //TODO: ADD BRANCH MANAGERS ABLE TO SEE
        ArrayList<String> staffIDsList = db.getBranchByBranchName(this.branchName).getStaffIDs();
        String branch = this.branchName;
        for (String staffID : staffIDsList){
            Staff staff = db.getStaff(staffID);
            System.out.println("StaffID: " + staff.getStaffID());
            System.out.println("Name: " + staff.getName());
            System.out.println("Role: " + staff.getUserType().stringFromUserType());
            System.out.println("Age: " + getAge());
            System.out.println("Gender: "+ getGender());
        }
    }
}
