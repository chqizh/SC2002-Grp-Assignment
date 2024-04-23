package Accounts;

import java.util.*;
import Branch.*;
import Customer.*;
import Database.InMemoryDatabase;

/**
 * This class represents a staff member, which is a specific type of employee with a branch association.
 */
public class Staff extends Employee implements IOrderProcess{
    private String branchName;
    private transient Scanner sc;

    /**
     * Constructs a new Staff member with the specified details.
     *
     * @param name     The name of the staff member.
     * @param gender   The gender of the staff member, represented as a char ('M' or 'F').
     * @param age      The age of the staff member.
     * @param branchName The name of the branch this staff member is associated with.
     */
    public Staff(String name, String staffID, char gender, int age, String branchName, InMemoryDatabase db) {
        super(name, staffID, UserType.STAFF, gender, age, db);
        this.branchName = branchName;
    }

    /**
     * Gets the branch name associated with this staff member.
     *
     * @return The branch name.
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * Sets the branch name for this staff member.
     *
     * @param branchname The new branch name.
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
            // TODO print status of order
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
            System.out.println("(4) Canclled");

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
}
