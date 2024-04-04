package Branch;

import Accounts.Customer;
import Menu.MenuItem;

public class Order {
    private int orderID;
    private Customer customer;
    private MenuItem orderItems[];
    private int numItems;

    public Order(int orderID, Customer customer){
        this.orderID = orderID;
        this.customer = customer;
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
