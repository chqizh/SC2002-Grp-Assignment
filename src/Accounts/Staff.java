package Accounts;

import java.util.HashMap;

import Branch.*;
import Customer.*;

public class Staff extends Employee{
    private int branchID;

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }
    
    public String getBranchID() {
        return branchID;
    }

    private Order getOrderFromBranch(Branches branches, int orderID){
        return branches.getSpecificBranch(branchID).getBranchOrders().getSpecificOrder(orderID);
    }

    //Display the new orders. 
    public void viewNewOrders(Branches branches, int orderID){
       //TODO
        while (true){
            HashMap<Integer, Order> orderList = branches.getSpecificBranch(branchID).getBranchOrders().getOrderList();
            for (Order order : orderList.values()){
                if (order.getOrderStatus().equals("New order.")){
                    // TODO printing function
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