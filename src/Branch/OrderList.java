package Branch;

import java.util.HashMap;
import Customer.*;

public class OrderList {
    // Create a HashMap with keys and values (orderID, orderDetails)
    // Basically like a dictionary in python.
    HashMap<Integer, OrderDetails> orderList = new HashMap<Integer, OrderDetails>();

    public void setOrderList(HashMap<Integer, OrderDetails> orderList) {
        //TODO
        this.orderList;
    }

    public Order[] getOrderList() {
        return orderList;
    }
}