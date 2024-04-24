package Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Menu.MenuItem;
import Branch.Branch;
//import Branch.OrderList;

/**
 * Represents an order placed by a customer.
 */
public class Order implements Serializable{

    /**
     * Enum representing the status of the order.
     */
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
    private boolean dineIn;
    //private OrderList orderListRef;
    
    /**
     * Constructs an order for a specified branch.
     *
     * @param branch The branch for which the order is being placed.
     */
    public Order(Branch branch){ 
        this.orderID = branch.getBranchOrders().nextOrderID();
        this.orderStatus = orderStatusFlags.NEW;
        this.branchName = branch.getBranchName(); 
        this.orderItems = new ArrayList<>(); 
    }

    /**
     * Gets the order ID.
     *
     * @return The order ID.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Sets the order ID.
     *
     * @param orderID The order ID to set.
     */
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    /**
     * Sets the branch name associated with the order.
     *
     * @param branchName The branch name to set.
     */
    public void setBranchName(String branchName){
        this.branchName = branchName;
    }

    /**
     * Gets the branch name associated with the order.
     *
     * @return The branch name.
     */
    public String getBranchName(){
        return this.branchName;
    }

    /**
     * Gets the list of items in the order.
     *
     * @return The list of order items.
     */
    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Adds an item to the order.
     *
     * @param item The item to add to the order.
     */
    public void addOrderItems(MenuItem item){
        this.orderItems.add(item);
    }

    /**
     * Gets the status of the order.
     *
     * @return The status of the order.
     */
    public orderStatusFlags getOrderStatus(){
        return this.orderStatus;
    }

    /**
     * Sets the status of the order.
     *
     * @param flag The status to set.
     */
    public void setOrderStatus(orderStatusFlags flag){
        if (this.orderStatus != flag){
            this.orderStatus = flag;
            if (flag == orderStatusFlags.PICKUP) this.scheduleAutoCancellation(15, TimeUnit.SECONDS);
            //if (flag == orderStatusFlags.COMPLETED) orderListRef.removeOrder(this.orderID);
        }
        else {
            System.out.println("Order is currently"  + this.orderStatus + "already.");
        }
    }

    /**
     * Gets the status of the order as a string.
     *
     * @return The status of the order as a string.
     */
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

    /**
     * Checks if the order is for dine-in.
     *
     * @return True if the order is for dine-in, false otherwise.
     */
    public boolean getDineIn(){
        return dineIn;
    }

    /**
     * Sets whether the order is for dine-in or not.
     *
     * @param value True if the order is for dine-in, false otherwise.
     */
    public void setDineIn(boolean value){
        this.dineIn = value;
    }

    /**
     * Schedules automatic order cancellation after a specified delay.
     *
     * @param delay The delay before cancellation.
     * @param unit The time unit of the delay.
     */
    private void scheduleAutoCancellation(int delay, TimeUnit unit) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(this::autoCancellation, delay, unit);
    }

    /**
     * Cancels the order automatically.
     *
     * @return True if the order was cancelled successfully, false otherwise.
     */
    private boolean autoCancellation() {
        this.orderStatus = orderStatusFlags.CANCELLED;
        //this.orderListRef.removeOrder(this.orderID);
        return true;
    }

    /**
     * Calculates the total price of the order.
     *
     * @return The total price of the order.
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        List<MenuItem> orderItems = getOrderItems();

        for (MenuItem item : orderItems) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    /**
     * Prints details of the order.
     */
    public void printOrder(){
        System.out.println("OrderID: " + orderID);
        System.out.println("Status: " + this.getOrderStatus());
        System.out.printf("%-8s %-8s %-20s %-10s %-15s\n", "OrderID", "Item ID", "Name", "Price", "Category");
        System.out.println("--------------------------------------------------------------------");

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