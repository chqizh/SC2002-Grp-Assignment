package Customer;
import Menu.*;
import java.util.ArrayList;

public class ReceiptGenerator {
    public void generateReceipt(Order order, String paymentMethod) {
        System.out.println("Receipt for Order ID: " + order.getOrderID());
        
        if(order.getDineIn()){
            System.out.println("Dine-In Order:");
        }
        else{
            System.out.println("Takeaway Order:");
        }
        
        ArrayList<MenuItem> orderItems = order.getOrderItems();
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-10s %-20s\n", "Item", "Price", "Customizations");
        System.out.println("------------------------------------------------");
        
        for (MenuItem item : orderItems) {
            System.out.printf("%-20s %-10s %-20s\n", item.getItemName(), item.getPrice(), item.getCustomisation());
        }
        
        System.out.println("------------------------------------------------");
        System.out.println("Total Price: $" + order.calculateTotalPrice());
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Thank you for your order!");
    }
}

