import java.lang.System;
import java.util.Scanner;
import java.io.*;

import Accounts.*;
import Branch.*;
import Customer.*;
import Display.*;
import Database.*;
import DataPersistence.*;

public class FOMSApp{
    // Declaring ANSI_RESET so that we can reset the color 
    public static final String ANSI_RESET = "\u001B[0m"; 
    // Declaring the color theme
    public static final String ANSI_CYAN = "\u001B[36m";
    
    private static final String DATA_STORE = "data_store.ser";
    private Scanner sc;
    private InMemoryDatabase db;

    public FOMSApp() {
        // Initialize scanner here to prevent resource leak
        sc = new Scanner(System.in);
        // Deserialization
        try {
            db = (InMemoryDatabase) SerializationUtil.deserialize(DATA_STORE);
        } catch (IOException | ClassNotFoundException e) {
            db = new InMemoryDatabase(); // Create a new one if no data is found
        }
    }

    public static void main(String[] args) {
        FOMSApp app = new FOMSApp();
        app.run();
        app.sc.close();
    }

    public void run() {
        System.out.println(ANSI_CYAN + "Welcome to the Fastfood Ordering Management System." + ANSI_RESET);
        System.out.println("Are you a customer? (Y/N)");
        boolean isCustomer = (sc.next().charAt(0) == 'Y');
        sc.nextLine(); // Consume the rest of the line
        if (isCustomer) {
            displayCustomerInterface();
        } else {
            System.out.println("Enter your staffID: ");
            String staffID = sc.nextLine();
            System.out.println("Enter your password: ");
            String password = sc.nextLine();
            // A method that returns the Account object after validation
            Account account = db.validateLogin(staffID, password);

            if (account != null) {
                UserType userType = account.getUserType();
                switch (userType) {
                    case STAFF:
                        displayStaffInterface(account);
                        break;
                    case BRANCH_MANAGER:
                        displayBranchManagerInterface(account);
                        break;
                    case ADMIN:
                        displayAdminInterface(account);
                        break;
                }
            } else {
                System.out.println("Login unsuccessful.");
            }
        }

        // Serialization
        try {
            SerializationUtil.serialize(db, DATA_STORE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayStaffInterface(Account account) {
        Staff staff = db.getStaffFromAccount(account);
        int choice;
        System.out.println("Please select your action: ");
        System.out.println("(1) Display New Orders ");
        System.out.println("(2) View Order Details ");
        System.out.println("(3) Process Order ");

        choice=sc.nextInt();

        switch(choice){
            case 1:
                staff.viewNewOrders();
                break;
            case 2:
                staff.viewOrder(int orderID);
                break;
            case 3:
                staff.processOrders(int orderID);
                break;
        }
    }

    private void displayBranchManagerInterface(Account account) {
        int choice;
        System.out.println("Please select your action: ");
        System.out.println("(1) Display New Orders  ");
        System.out.println("(2) View Order Details ");
        System.out.println("(3) Process Order ");
        System.out.println("(4) Display Staff List ");
        System.out.println("(5) Add Menu Item ");
        System.out.println("(6) Edit Menu Item");
        System.out.println("(7) Remove Menu Item ");
 
 
        choice = sc.nextInt();
        switch(choice){
            case 1:
                Manager.viewNewOrders()
                break;
            case 2:
                Manager.viewOrder(int orderID);
                break;
            case 3:
                Manager.processOrders(int orderID);
                break;
            case 4:
                Manager.displayStaffList();
                break;
            case 5:
                menu.addItems();
                break;
            case 6:
                System.out.println("What do you want to update? ");
                System.out.println("(1) Name");
                System.out.println("(2) Price");
                System.out.println("(3) Availability");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        menu.updateName();
                        break;
                    case 2:
                        menu.updatePrice();
                        break;
                    case 3:
                        menu.updateAvailability();
                        break;
                }

            case 7:
                menu.deleteItems();
                break;
        }
  
    }

    private void displayAdminInterface(Account account) {
        int choice = sc.nextInt();
       switch (choice){
           case 1:
               admin.addStaff(staffID);
		        break;
           case 2:
               admin.removeStaff(staffID);
		        break;
           case 3:
               admin.editStaff(staffID);
		        break;
           case 4:
               //Display staff list (filter: branch, role, gender, age).
               System.out.println("Filter by: (1) Branch, (2) Role, (3) Gender or (4) Age. Please enter choice number.");
               int filterChoice = sc.nextInt();
               admin.displayStaffList(filterChoice);
           case 5:
               //Assign managers to each branch within the quota constraint.
               admin.assignManagers(staffID);
		        break;
           case 6:
               // Promote a staff to a Branch manager.
               admin.promoteStaff(staffID);
		        break;
           case 7:
               //Transfer a staff/manager among branches.
               admin.transferStaff(staffID, newBranchID);
		        break;
           case 8:
               // Add/remove payment method
               admin.editPaymentMethod(paymentMethod);
		        break;
           case 9:
               // Open/close branch
               admin.openCloseBranch(branchID);
		        break;
    }
}

    private void displayCustomerInterface() {
        System.out.println("(1) Browse Menu");
        System.out.println("(2) Add to Cart");
        System.out.println("(3) Delete from Cart");
        System.out.println("(4) View Cart");
        System.out.println("(5) Place Order");
        System.out.println("(6) Track Order");
        System.out.println("(7) Exit System");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                cust.browseMenu();
                break;
            case 2:
                cust.addToCart();
                break;
            case 3:
                cust.deleteFromCart();
                break;
            case 4:
                cust.viewCart();
                break;
            case 5:
                cust.placeOrder(branchID);
                break;
            case 6:
                cust.trackOrder(branchID);
                break;
            case 7:
                flag=0;
                break;
            default:
                System.out.println("You have entered an invalid input! Try again.");
                break;
        }

    }
}
