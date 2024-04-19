package Customer;
import Branch.Menu;
import Menu.MenuItem;

import java.util.List;

import Branch.Branch;

public class Customer{
    private Branch selectedBranch;
    private List<MenuItem>cart;
    private static int nextOrderID =1;
    private List<Order> orders;

    public void selectBranch(Branch branch){
        this.selectedBranch = branch;
    }

    public void browseMenu(Menu menu){
        menu.printMenuItems();
    }

    public void addToCart(MenuItem item){
        cart.add(item);
        System.out.println("Item "+item.getItemName()+" added to cart.");
    }

    public void deleteFromCart(int itemID) {
        boolean removed = false;
        for (int i = 0; i < cart.size(); i++) {
            MenuItem item = cart.get(i);
            if (item.getItemID() == itemID) {
                cart.remove(i);
                removed = true;
                System.out.println("Item with ID " + itemID + " removed from cart.");
                break;
            }
        }
        if (!removed) {
            System.out.println("Item with ID " + itemID + " not found in cart.");
        }
    }


    public OrderDetails placeOrder(int branchID) {
        if (cart.isEmpty()) {
            System.out.println("Cannot place an empty order.");
            return null;
        }
        
        // Create a new order
        int orderID = generateOrderID();
        OrderDetails orderDetails = new OrderDetails(orderID, branchID, cart);
        
        orders.add(new Order(orderID, branchID));
        
        cart.clear();
        
        System.out.println("Order placed successfully!");
        return orderDetails;
    }

    public void trackOrder(int orderId) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getOrderID() == orderId) {
                System.out.println("Order status: " + order.getOrderStatus());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Order not found.");
        }
    }
    

    public int generateOrderID(){
        return nextOrderID++;
    }
}