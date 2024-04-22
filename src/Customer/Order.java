package Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import Database.InMemoryDatabase;
import Menu.MenuItem;
import Branch.Branch;

public class Order {
    public enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED,
        CANCELLED
    }

    private int orderID;
    private orderStatusFlags orderStatus;
    private String branchName;
    private ArrayList<MenuItem> orderItems;
    private static int nextOrderID = 1;
    private Branch branch;
    private String customisation;
    private boolean dineIn;
    private Timer pickupTimestamp;

    public Order(Branch branch){ 
        this.orderID = branch.nextOrderID();
        this.orderStatus = orderStatusFlags.NEW;
        this.branchName = branch.getBranchName(); 
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

    public void setBranchName(String branchName){
        this.branchName = branchName;
    }

    public String getBranchName(){
        return this.branchName;
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

    public String getOrderStatusString(){
        switch (this.orderStatus){
            case orderStatusFlags.NEW:
                return "New order.";
            case orderStatusFlags.PROCESSED:
                return "Order has been processed.";
            case orderStatusFlags.PICKUP:
                return "Order is ready to pickup.";
            case orderStatusFlags.COMPLETED:
                return "Order has been completed.";
            case orderStatusFlags.CANCELLED:
                return "Order has been cancelled.";
            default:
                return "Invalid.";
        }
    }


    // public void placeOrder() throws IOException{
    public void placeOrder(){
        branch.getBranchOrders().addOrder(this);
    }

    public boolean getDineIn(){
        return dineIn;
    }

    public void setCustomisation(String customisation){
        this.customisation = customisation;
    }

    public String getCustomisation(){
        return this.customisation;
    }

    public void setDineIn(boolean value){
        this.dineIn = value;
    }

    public boolean autoCancellation(){
        //FutureTask class or something (cancel 30mins after ready to pickup if not collected)
        return true;
    }

    public void printOrder(){
        System.out.println("OrderID: " + orderID);
    }
}