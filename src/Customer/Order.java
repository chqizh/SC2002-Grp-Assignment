package Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private String customisation;
    private boolean dineIn;

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

    public void addOrderItems(MenuItem item){
        this.orderItems.add(item);
    }

    public orderStatusFlags getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(orderStatusFlags Flag){
        if (this.orderStatus != Flag){
            this.orderStatus = Flag;
            if (Flag == orderStatusFlags.PICKUP) this.scheduleAutoCancellation(15, TimeUnit.SECONDS);
        }
        else {
            System.out.println("Order is currently"  + this.orderStatus + "already.");
        }
    }

    public String getOrderStatusString(){
        switch (this.orderStatus){
            case NEW:
                return "New order.";
            case PROCESSED:
                return "Order has been processed.";
            case PICKUP:
                return "Order is ready to pickup.";
            case COMPLETED:
                return "Order has been completed.";
            case CANCELLED:
                return "Order has been cancelled.";
            default:
                return "Invalid.";
        }
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

    private void scheduleAutoCancellation(int delay, TimeUnit unit) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(this::autoCancellation, delay, unit); // Schedule the autoCancellation task to run after 15 minutes
    }

    private boolean autoCancellation() {
        this.orderStatus = orderStatusFlags.CANCELLED;
        return true;
    }

    public void printOrder(){
        System.out.println("OrderID: " + orderID);
        System.out.printf("%-8s %-8s %-20s %-10s %-15s\n", "OrderID", "Item ID", "Name", "Price", "Category");
        System.out.println("--------------------------------------------------------------------");

        // Display menu items
        //ArrayList<MenuItem> menuItems = menu.getMenuItems();
        for (MenuItem orderItem : orderItems) {
            System.out.printf("%-8d %-8d %-20s $%-10.2f %-15s\n",
                                this.orderID,
                                orderItem.getItemID(), 
                                orderItem.getItemName(), 
                                orderItem.getPrice(), 
                                orderItem.getCategory());
        }
    }
}