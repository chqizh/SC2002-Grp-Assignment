package Customer;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

import Menu.*;
import Branch.*;
import Customer.Order.orderStatusFlags;
import Database.InMemoryDatabase;

public class Customer implements ICustomerOrderProcess, Serializable{
    private ArrayList<MenuItem> cart;
    private transient Scanner sc;
    private InMemoryDatabase db;

    public Customer(InMemoryDatabase db){
        this.cart = new ArrayList<>();
        this.db = db;
    }

    public void browseMenu(String branchName){
        Branch branch = db.getBranchByBranchName(branchName);
        MenuDisplay menudisplay = new MenuDisplay(branch.getBranchMenu());
        menudisplay.displayMenu();
    }

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
            System.out.println("Item with ID " + index+1 + " removed from cart.");
        } else {
            System.out.println("Failed to remove item from cart.");
        }
    }

    //public void placeOrder(string branchName) throws IOException {
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
                //System.out.println("Your Order ID is:"+ order.getOrderID());
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
            System.out.println("Your order cannot be collected at this time. Your order is still being prepared...");
            return;
        }
        order.setOrderStatus(orderStatusFlags.COMPLETED);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (MenuItem item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

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

    private boolean processPayment(Payment paymentMethod, double amount) {
        return paymentMethod.processPayment(amount);
    }

}
    
