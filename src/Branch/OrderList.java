package Branch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import Customer.Order;

/**
 * Manages a collection of orders within a branch, providing functionality to add,
 * remove, and retrieve orders using unique order IDs.
 */
public class OrderList implements Serializable {
    private HashMap<Integer, Order> orderMap; // Stores orders with their IDs as keys
    private int maxOrderID; // Tracks the maximum order ID issued

    /**
     * Constructs a new OrderList, initializing an empty collection of orders and
     * setting the initial maximum order ID to a random number.
     */
    public OrderList() {
        this.orderMap = new HashMap<>();
        this.maxOrderID = 9790;
    }

    /**
     * Adds an order to the list. The order ID of the order is used as the key.
     *
     * @param order The order to be added to the list.
     */
    public void addOrder(Order order) {
        orderMap.put(order.getOrderID(), order);
    }

    /**
     * Removes an order from the list based on the provided order ID.
     *
     * @param orderID The ID of the order to be removed.
     */
    public void removeOrder(int orderID) {
        orderMap.remove(orderID);
    }

    /**
     * Retrieves an order by its order ID.
     *
     * @param orderID The ID of the order to retrieve.
     * @return The Order if it exists in the list; otherwise, returns null.
     */
    public Order getOrder(int orderID) {
        return orderMap.get(orderID);
    }

    /**
     * Gets a list of all orders currently managed by this OrderList.
     *
     * @return An ArrayList of all orders.
     */
    public ArrayList<Order> getOrderList() {
        return new ArrayList<>(orderMap.values());
    }

    /**
     * Generates and returns the next valid order ID, incrementing the internal
     * counter to ensure uniqueness.
     *
     * @return The next unique order ID.
     */
    public int nextOrderID() {
        return ++this.maxOrderID;
    }
}
