package Accounts;

import Branch.*;

public class Staff extends Employee{
    private int branchID;

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }
    
    public String getBranchID() {
        return branchID;
    }

    //Display the new orders. 
    public void viewNewOrders(){
       //TODO
        ;
    }

    //View the details of a particular order. 
    public void viewOrderDetails(int orderID){
        //TODO
        ;
    }

    //Process order: select order to process, update the status of the processed order from a new order to be “Ready to pickup”.
    public boolean processOrder(Branches branches, int orderID){
        branches.getBranchesList()[branchID].orderList ;
    }
}