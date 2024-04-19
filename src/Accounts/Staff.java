package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;
import Database.*;

/**
 * This class represents a staff member, which is a specific type of employee with a branch association.
 */
public class Staff extends Employee implements IOrderProcess{
    private String branchID;

    /**
     * Constructs a new Staff member with the specified details.
     *
     * @param name     The name of the staff member.
     * @param gender   The gender of the staff member, represented as a char ('M' or 'F').
     * @param age      The age of the staff member.
     * @param branchID The ID of the branch this staff member is associated with.
     */
    public Staff(String name, String staffID, char gender, int age, String branchID) {
        super(name, staffID, UserType.STAFF, gender, age);
        this.branchID = branchID;
    }

    /**
     * Gets the branch ID associated with this staff member.
     *
     * @return The branch ID.
     */
    public String getBranchID() {
        return branchID;
    }

    /**
     * Sets the branch ID for this staff member.
     *
     * @param branchID The new branch ID.
     */
    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    // From IOrderProcess
    public void viewNewOrders(Branch branch) {
        System.out.println("New Orders:");
        branch.getBranchOrders().getOrderList().values().stream()
                .filter(order -> order.getOrderStatus() == Order.orderStatusFlags.NEW) // Assuming OrderStatus enum
                .forEach(System.out::println); // Print each order (implement toString in Order for better output)
    }

    public void viewOrder(Branch branch, int orderID) {
        Order order = branch.getBranchOrders().getSpecificOrder(orderID);
        if (order != null) {
            System.out.println(order); // Assuming toString() in Order is overridden
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }

    public void processOrders(Branch branch, int orderID) {
        Scanner  sc = new Scanner(System.in);
        Order order = branch.getBranchOrders().getSpecificOrder(orderID);
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


}
