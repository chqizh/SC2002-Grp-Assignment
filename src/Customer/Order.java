package Customer;
import java.util.ArrayList;
import java.util.List;

import Branch.MenuItem;

public class Order {
    private int orderID;
    public enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED
    }
    private orderStatusFlags orderStatus;
    private int branchID;
    private List<OrderDetails> orderItems;
    private int numItems;

    public Order(int orderID, int branchID){
        this.orderID = orderID;
        this.orderStatus = orderStatusFlags.PROCESSED;
        this.branchID = branchID;
        //this.orderItems = new OrderDetails[1000];
        this.orderItems = new ArrayList<>();
        this.numItems = 0;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<OrderDetails> getOrderItems() {
        return orderItems;
    }
    
    public void placeOrder(OrderDetails orderDetail){
        this.orderItems.add(orderDetail);
        numItems++;
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