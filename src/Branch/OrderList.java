package Branch;
//import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import Customer.Order;

public class OrderList implements Serializable{
    // <orderID, order>
    private HashMap<Integer, Order> orderMap;

    public OrderList (){
        orderMap = new HashMap<Integer, Order>();
    }

    public void addOrder(Order order){
        orderMap.put(order.getOrderID(), order);
    }

    public void removeOrder(int orderID) {
        orderMap.remove(orderID);
    }

    public Order getOrder(int orderID) {
        return orderMap.get(orderID);
    }

    public ArrayList<Order> getOrderList(){
        ArrayList<Order> orderList = new ArrayList<Order>(orderMap.values());
        return orderList;
    }
}
