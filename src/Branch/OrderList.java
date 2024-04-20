package Branch;
import java.util.HashMap;
import Customer.Order;

public class OrderList {
    private HashMap<Integer, Order> orderMap;

    public OrderList() {
        orderMap = new HashMap<Integer, Order>();
    }

    public void addOrder(Order order) {
        orderMap.put(order.getOrderID(), order);
    }

    public void removeOrder(int orderID) {
        orderMap.remove(orderID);
    }

    public Order getOrder(int orderID) {
        return orderMap.get(orderID);
    }
}