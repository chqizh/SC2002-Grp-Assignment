package Accounts;

import java.util.*;

import Branch.*;
import Customer.*;

public class Staff extends Employee{
    private int branchID;

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }
    
    public int getBranchID() {
        return this.branchID;
    }

    private Order getOrderFromBranch(Branches branches, int orderID){
        return branches.getSpecificBranch(branchID).getBranchOrders().getSpecificOrder(orderID);
    }

    //Display the new orders. 
    public void viewNewOrders(Branches branches, int orderID){
       //TODO
        while (true){
            HashMap<Integer, Order> orderList = branches.getSpecificBranch(branchID).getBranchOrders().getOrderList();
            // Goes through every order to find new orders.
            for (Order order : orderList.values()){
                if (order.getOrderStatus().equals("New order.")){
                    //Prints every item in order.
                    String format = "%-20s%-20s%s%n";
                    System.out.printf(format, "Item Name", "Qty", "Total Price");
                    for (int i = 0; i < order.getOrderItems().length; i++){
                        // TODO printing function may need to move into a seperate class.
                        // The second string begins after 20 characters. The dash means that the first string is left-justified.
                        System.out.printf(format, order.getOrderItems()[i]);
                    }
                }
            }
        }
    } 

    //View the details of a particular order. 
    public void viewOrderDetails(Branches branches, int orderID){
        OrderDetails order[] = getOrderFromBranch(branches, orderID).getOrderItems();
        //TODO printing function
    }

    //Process order: select order to process, update the status of the processed order from a new order to be “Ready to pickup”.
    public boolean processOrder(Branches branches, int orderID){
        return getOrderFromBranch(branches, orderID).updateOrderStatus();
    }
}