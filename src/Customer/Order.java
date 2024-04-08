package Customer;

import Branch.MenuItem;

public class Order extends Customer {
    private int orderID;
    enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED
    }
    private orderStatusFlags orderStatus;
    private String branchID;
    private OrderDetails orderItems[];
    private int numItems;

    public Order(int orderID, String branchID){
        this.orderID = orderID;
        this.orderStatus = orderStatusFlags.PROCESSED;
        this.branchID = branchID;
        this.orderItems = new OrderDetails[1000];
        this.numItems = 0;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public OrderDetails[] getOrderItems() {
        return orderItems;
    }
    
    public void placeOrder(MenuItem orderItem){
        this.orderItems[numItems] = orderItem;
        numItems++;
    }

    public String getOrderStatus(){
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
