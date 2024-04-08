package Branch;

import java.util.HashMap;
import Customer.*;

public class OrderList {
    // Create a HashMap with keys and values (orderID, orderDetails)
    // Basically like a dictionary in python.
    HashMap<Integer, Order> orderList = new HashMap<Integer, Order>();

    public void setOrderList(HashMap<Integer, Order> orderList) {
        //TODO
    }

    public HashMap<Integer, Order> getOrderList() {
        return orderList;
    }

    public Order getSpecificOrder(int orderID){
        return orderList.get(orderID).keys;
    }
}