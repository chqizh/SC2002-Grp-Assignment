package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;
import Database.*;

public class BranchManager extends Employee implements IOrderProcess, IMenuManagement, IStaffManagement {
    private String branchName;

	public BranchManager(String name, String staffID, char gender, int age, String branchID) {
        super(name, staffID, UserType.BRANCH_MANAGER, gender, age);
        this.branchName = branchID;
    }

    /**
     * Gets the branch name associated with this branch manager.
     *
     * @return The branch name.
     */
    public String getBranchName() {
        return branchName;
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
                .forEach(System.out::println); // Print each order (implement toString in Order for better output)
    }

    public void viewOrder(Branch branch, int orderID) {
        Order order = branch.getBranchOrders().getOrder(orderID);
        if (order != null) {
            System.out.println(order); // Assuming toString() in Order is overridden
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }

    public void processOrders(Branch branch, int orderID) {
        Scanner sc = new Scanner(System.in);
        Order order = branch.getBranchOrders().getOrder(orderID);
        if (order != null) {
            System.out.println("Current status is " + order.getOrderStatus());
            System.out.println("Pick update action:");
            System.out.println("(1) Processed");
            System.out.println("(2) Pickup");
            System.out.println("(3) Completed");

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
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
            System.out.println("Order ID " + orderID + " has been updated to" + order.getOrderStatus());
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }


// From IMenuManagement
    public void addMenuItem(Branch branch){};

    public void removeMenuItem(Branch branch){};

    public void editMenuItem(Branch branch){};

// From IStaffManagement
	public void displayStaffList(){
    ArrayList<String> staffIDsList = InMemoryDatabase.getStaffIDs();
                String branch = this.branchName;
                for (String staffID : staffIDsList){
                    Staff staff = InMemoryDatabase.getStaff(staffID);
                    if (staff.getBranchName() == branch){
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Name: " + staff.getName());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Age: " + getAge());
                        System.out.println("Gender: "+ getGender());
                    }
                }
            }
}
