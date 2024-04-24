package Customer;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

import Menu.*;
import Branch.*;
import Customer.Order.orderStatusFlags;
import Database.InMemoryDatabase;

/**
 * Represents a customer who can browse menus, add items to cart, place orders, track orders, and collect orders.
 * Implements interface for Customer Order Processing.
 */
public class Customer implements ICustomerOrderProcess, Serializable{
    private ArrayList<MenuItem> cart;
    private transient Scanner sc;
    private InMemoryDatabase db;

    /**
     * Constructs a Customer object with the specified database.
     *
     * @param db The database for the customer.
     */
    public Customer(InMemoryDatabase db){
        this.cart = new ArrayList<>();
        this.db = db;
    }

    /**
     * Allows the customer to browse the menu of a specified branch.
     *
     * @param branchName The name of the branch whose menu to browse.
     */
    @Override
    public void browseMenu(String branchName){
        Branch branch = db.getBranchByBranchName(branchName);
        MenuDisplay menudisplay = new MenuDisplay(branch.getBranchMenu());
        menudisplay.displayMenu();
    }

    /**
     * Allows the customer to view the contents of their cart.
     */
    @Override
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart Contents:");
            System.out.println("--------------------------------------------------------");
            System.out.printf("%-5s %-25s %-15s %-10s\n", "ID", "Name", "Price", "Customizations");
            System.out.println("--------------------------------------------------------");
            int i = 1;
            for (MenuItem item : cart) {
                System.out.printf("%-5d %-25s %-15s %-10s\n", i ,item.getItemName() ,item.getPrice(), item.getCustomisation());
                i++;
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    /**
     * Allows the customer to add items to their cart from a specified branch's menu.
     *
     * @param branchName The name of the branch from which to add items to the cart.
     */
    @Override
    public void addToCart(String branchName){
        Menu menu = db.getBranchByBranchName(branchName).getBranchMenu();
        ArrayList<MenuItem> menuItems = menu.getMenuItemsList();
        
        sc = new Scanner(System.in);
        System.out.print("Enter the item's ID you would like to order: ");
        int itemID = sc.nextInt();
        sc.nextLine();

        MenuItem cartItem = null;
        for (MenuItem item : menuItems){
            if(item.getItemID() == itemID){
                cartItem = new MenuItem(item.getItemID(), item.getItemName(), item.getPrice(), item.getCategory(), item.getBranchName());
                break;
            }
        }

        if (cartItem != null) {
            System.out.println("Do you have any customisations for this item? (Y/N)");
            char choice = sc.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                System.out.print("Enter your customisations: ");
                String customisations = sc.nextLine();
                cartItem.setCustomisation(customisations);
            }
            cart.add(cartItem);
            System.out.printf("%s added to cart!\n", cartItem.getItemName());
        }
        else {
            System.out.printf("Item with itemID %s not found in the menu.\n ", itemID);
        }
    }

    /**
     * Allows the customer to delete an item from their cart.
     */
    @Override
    public void deleteFromCart() {
        sc = new Scanner(System.in);
        System.out.println("Enter Item ID that you would like to remove:");
        int index = sc.nextInt();
        sc.nextLine();
        index= index-1;
        if (index < 0 || index >= cart.size()) {
            System.out.println("Invalid index. Please enter a valid index within the range of items in the cart.");
            return;
        }
    
        MenuItem removedItem = cart.remove(index);
        if (removedItem != null) {
            index=index+1;
            System.out.println("Item with ID " + index + " removed from cart.");
        } else {
            System.out.println("Failed to remove item from cart.");
        }
    }

    /**
     * Places an order for the items currently in the cart.
     *
     * @param branchName The name of the branch to place the order with.
     */
    @Override
    public void placeOrder(String branchName){
        int option;
        Branch branch = db.getBranchByBranchName(branchName);
        if (branch == null){
            System.out.println("Invalid branch entered.");
            return;
        }

        if (cart.isEmpty()) {
            System.out.println("Cannot place an empty order.");
            return;
        }
        do {
            System.out.println("(1) Dine-In");
            System.out.println("(2) Takeaway");
            System.out.println("Enter your choice [1 or 2]:");
            option = sc.nextInt();
            sc.nextLine();
        } while ((option!=1) && (option!=2));
        

        double totalPrice = calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);

        sc = new Scanner(System.in);
        System.out.println("Select Payment Method:");
        // TODO loop to check for boolean first. 
        System.out.println("1. Bank Card");
        System.out.println("2. PayPal");
        System.out.println("3. PayNow");
        int choice = sc.nextInt();
        sc.nextLine(); 

        Payment paymentMethod = selectPaymentMethod(choice);

        if (paymentMethod != null) {
            if (processPayment(paymentMethod, totalPrice)) {
                Order order = new Order(branch);

                if(option==1){
                    order.setDineIn(true);
                }
                else{
                    order.setDineIn(false);
                }

                for (MenuItem item : cart){
                    order.addOrderItems(item);
                }
                
                branch.addOrder(order);
                System.out.println("Order placed successfully.");
                ReceiptGenerator receipt = new ReceiptGenerator();
                receipt.generateReceipt(order, paymentMethod.getPaymentMethodName());
                cart.clear();
            }
            else {
                System.out.println("Payment failed. Order not placed.");
            }
        } 
        else {
            System.out.println("Invalid payment method selected.");
        }
    }

    /**
     * Tracks the status of an order placed with a specified branch.
     *
     * @param branchName The name of the branch to track the order with.
     */
    @Override
    public void trackOrder(String branchName) {
        sc = new Scanner(System.in);
        System.out.println("Enter Order ID:");
        int orderID = sc.nextInt();
        sc.nextLine(); // Consume newline character
        
        Branch branch = db.getBranchByBranchName(branchName);
        if (branch != null){
            Order order = branch.getBranchOrders().getOrder(orderID); // Retrieve the order from the branch's order list
            System.out.println(order.getOrderStatusString());
        }
        else System.out.println("Invalid branch entered.");
    }

    /**
     * Allows the customer to collect an order from a specified branch.
     *
     * @param branchName The name of the branch from which to collect the order.
     */
    @Override
    public void collectOrder(String branchName){
        Branch branch = db.getBranchByBranchName(branchName);
        if(branch==null){
            System.out.println("Invalid branch entered.");
            return;
        }

        sc = new Scanner(System.in);
        System.out.println("Enter Order ID:");
        int orderID = sc.nextInt();
        sc.nextLine();
        Order order = branch.getBranchOrders().getOrder(orderID);
        if(order == null){
            System.out.println("Order not found.");
            return;
        }
        if(order.getOrderStatus()!=Order.orderStatusFlags.PICKUP){
            System.out.println("Your order cannot be collected at this time. Your order is still being prepared or has been cancelled.");
            return;
        }
        order.setOrderStatus(orderStatusFlags.COMPLETED);
    }

    /**
     * Calculates the total price of items in the cart.
     *
     * @return The total price of items in the cart.
     */
    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (MenuItem item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    /**
     * Selects a payment method based on the customer's choice.
     *
     * @param choice The customer's choice of payment method.
     * @return The selected payment method.
     */
    private Payment selectPaymentMethod(int choice) {
        switch (choice) {
            case 1:
                return new BankCard("1234567890123456", "12/25", "123");
            case 2:
                return new Paypal("example@example.com", "password");
            case 3:
                return new PayNow("12345678");
            default:
                return null;
        }
    }
    /**
     * Processes the payment for the order using the selected payment method.
     *
     * @param paymentMethod The selected payment method.
     * @param amount        The total amount to be paid.
     * @return True if the payment was successful, false otherwise.
     */
    private boolean processPayment(Payment paymentMethod, double amount) {
        return paymentMethod.processPayment(amount);
    }

}
    
