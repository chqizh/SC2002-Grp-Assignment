package Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Menu.MenuItem;
import Branch.Branch;
import Branch.OrderList;

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
    private String customisation;
    private boolean dineIn;
    private OrderList orderListRef;

    public Order(Branch branch){ 
        this.orderID = branch.nextOrderID();
        this.orderStatus = orderStatusFlags.NEW;
        this.branchName = branch.getBranchName(); 
        this.orderItems = new ArrayList<>(); 
        this.orderListRef = branch.getBranchOrders();
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

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItems(MenuItem item){
        this.orderItems.add(item);
    }

    public orderStatusFlags getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(orderStatusFlags flag){
        if (this.orderStatus != flag){
            this.orderStatus = flag;
            if (flag == orderStatusFlags.PICKUP) this.scheduleAutoCancellation(15, TimeUnit.SECONDS);
            if (flag == orderStatusFlags.COMPLETED) orderListRef.removeOrder(this.orderID);
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

    public void setDineIn(boolean value){
        this.dineIn = value;
    }

    private void scheduleAutoCancellation(int delay, TimeUnit unit) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(this::autoCancellation, delay, unit); // Schedule the autoCancellation task to run after 15 minutes
    }

    private boolean autoCancellation() {
        this.orderStatus = orderStatusFlags.CANCELLED;
        this.orderListRef.removeOrder(this.orderID);
        return true;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        List<MenuItem> orderItems = getOrderItems();

        for (MenuItem item : orderItems) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
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