package Branch;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import Customer.Order;
import DataPersistence.*;

public class OrderList implements Serializable{
    // <orderID, order>
    private HashMap<Integer, Order> orderMap;

    OrderList (){
        orderMap = new HashMap<Integer, Order>();
    }

    /*
    public OrderList() throws IOException {
        try {
            orderMap = (HashMap<Integer, Order>) SerializationUtil.deserialize("orderList.ser");
            //System.out.println("Menu deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            //System.err.println("Deserialization failed: " + e.getMessage());
            // Initialize menu to an empty ArrayList if deserialization fails
            orderMap = new HashMap<Integer,Order>();
        }
    }
    */

  /*   public void addOrder(Order order) throws IOException {
        orderMap.put(order.getOrderID(), order);
        SerializationUtil.serialize(orderMap, "orderList.ser");
    }

    public void removeOrder(int orderID) throws IOException {
        orderMap.remove(orderID);
        SerializationUtil.serialize(orderMap, "orderList.ser");
    }
 */

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
