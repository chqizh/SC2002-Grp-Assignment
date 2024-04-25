package Customer;
import Menu.*;
import java.util.ArrayList;

/**
 * Provides functionality to generate a detailed receipt for an order.
 */
public class ReceiptGenerator {

    /**
     * Generates a detailed receipt for a given order and the payment method used.
     * The receipt includes order details such as the order type, items ordered, 
     * their prices, any customizations, and the total price.
     *
     * @param order The order for which the receipt is generated.
     * @param paymentMethod The method of payment used for the order.
     */
    public void generateReceipt(Order order, String paymentMethod) {
        System.out.println("════════════════════════════════════════════════════════");
        if (order.getDineIn()) {
            System.out.println(" Dine-In Order                                    ");
        } else {
            System.out.println(" Takeaway Order                                   ");
        }
        System.out.println(" Receipt for Order ID: " + String.format("%-29s", order.getOrderID()));
        System.out.println(" Branch: " + String.format("%-41s", order.getBranchName()));

        ArrayList<MenuItem> orderItems = order.getOrderItems();
        System.out.println("-------------------------------------------------------");
        System.out.printf(" %-24s | %-6s | %-24s \n", "Item", "Price", "Customizations");
        System.out.println("-------------------------------------------------------");

        for (MenuItem item : orderItems) {
            System.out.printf(" %-24s | $%-5.2f | %-24s \n", item.getItemName(), item.getPrice(), item.getCustomisation());
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf(" Total Price: $%-38.2f \n", order.calculateTotalPrice());
        System.out.printf(" Payment Method: %-36s \n", paymentMethod);
        System.out.println(" Thank you for your order!                        ");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }
}

