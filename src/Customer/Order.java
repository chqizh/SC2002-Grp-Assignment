package Customer;
import java.util.ArrayList;
import java.util.List;

import Menu.MenuItem;

public class Order {
    public enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED
    }

    private int orderID;
    private orderStatusFlags orderStatus;
    private int branchID;
    private ArrayList<MenuItem> orderItems;
    //private int numItems;
    private static int nextOrderID = 1;

    public Order(int branchID){
        this.orderID = orderID;
        this.orderStatus = orderStatusFlags.PROCESSED;
        this.branchID = branchID;
        this.orderItems = new ArrayList<>();
    }

    public int generateOrderID (){
        return Order.nextOrderID++;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public orderStatusFlags getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(orderStatusFlags Flag){
        if (this.orderStatus != Flag){
            this.orderStatus = Flag;
        }
        else {
            System.out.println("Order is currently"  + this.orderStatus + "already.");
        }
    }

    // wrote another setter and getter for the Flag class ^

    public String checkOrderStatus(){
        if (orderStatus == orderStatusFlags.NEW){
            return "New order.";
        }
        else if (orderStatus == orderStatusFlags.PROCESSED){
            return "Order processed.";
        }
        else if (orderStatus == orderStatusFlags.PICKUP){
            return "Ready to pickup.";
        }
        else return "Completed.";
    }

    public boolean updateOrderStatus(){
        if (this.orderStatus == orderStatusFlags.NEW){
            this.orderStatus = orderStatusFlags.PROCESSED;
            return true;
        }
        else if (this.orderStatus == orderStatusFlags.PROCESSED){
            this.orderStatus = orderStatusFlags.PICKUP;
            return true;
        }
        else if (this.orderStatus == orderStatusFlags.PICKUP){
            this.orderStatus = orderStatusFlags.COMPLETED;
            return true;
        }
        return false;
    }
    
}