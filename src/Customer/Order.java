package Customer;

import Branch;

public class Order extends Customer {
    private int orderID;
    private String orderStatus;
    private String branchID;
    private OrderDetails orderItems[];
    private int numItems;

    public Order(int orderID, String branchID){
        this.orderID = orderID;
        this.orderStatus = "Pending";
        this.branchID = branchID;
        this.orderItems = new MenuItem[1000];
        this.numItems = 0;
    }

    public int getOrderID() {
        return orderID;
    }

    public void placeOrder(MenuItem orderItem){
        this.orderItems[numItems] = orderItem;
        numItems++;
    }
    
}
