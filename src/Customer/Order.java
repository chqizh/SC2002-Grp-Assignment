package Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Branch.OrderList;
import Menu.MenuItem;
import Branch.Branch;

public class Order {
    public enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED
    }

    private int orderID;
    private orderStatusFlags orderStatus;
    // private int branchID;
    private String branchName;
    private ArrayList<MenuItem> orderItems;
    //private int numItems;
    private static int nextOrderID = 1;
    private OrderList orderList;
    public Branch branch;

/*     public Order(int branchID){
        this.orderID = orderID;
        this.orderStatus = orderStatusFlags.PROCESSED;
        this.branchID = branchID;
        this.orderItems = new ArrayList<>();
    } */

        // im not sure if this version i did is correctly done. This is constructor?- KH
    public Order(String branchName){ 
        this.orderID = orderID; // shld we assign to static/const variable?
        this.orderStatus = orderStatusFlags.PROCESSED; // why processed?
        this.branchName = branchName; 
        this.orderItems = new ArrayList<>(); 
    }

    public int generateOrderID (){
        return Order.nextOrderID++;
    }

    public int getOrderID() {
        return orderID;
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

    // public void placeOrder() throws IOException{
    public void placeOrder(){
        branch.getBranchOrders().addOrder(this);
    }
}