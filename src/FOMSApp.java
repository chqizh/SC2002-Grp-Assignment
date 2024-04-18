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
    public static final String ANSI_CYAN = "\u001B[36m";\
    
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
        // Display options specific to staff members
        // E.g., view orders, process orders, etc.
    }

    private void displayBranchManagerInterface(Account account) {
        // Display options for branch managers
        // E.g., manage staff list, edit menu, etc.
    }

    private void displayAdminInterface(Account account) {
        // Display options for admin users
        // E.g., add/remove staff, manage branches, etc.
        System.out.println("Enter your choice.")
        

        int choice = sc.nextInt();
        switch (choice):
            case 1:
                account.addStaff();
                break;
            case 2: 
                account.removeStaff();
            case 3:
                account.editStaff();
            case 4:
                //Display staff list (filter: branch, role, gender, age). 
                System.out.println("Filter by: (1) Branch, (2) Role, (3) Gender or (4) Age. Please enter choice number.");
                int filterChoice = sc.nextInt();
                account.displayStaffList(filterChoice);
            case 5:
                //Assign managers to each branch within the quota constraint.
                account.assignManagers(staffID);
            case 6:
                // Promote a staff to a Branch manager. 
                account.promoteStaff(staffID);
            case 7:
                //Transfer a staff/manager among branches. 
                account.transferStaff(staffID, newBranchID);
            case 8:
                // Add/remove payment method
                account.editPaymentMethod(paymentMethod);
            case 9:
                // Open/close branch
                account.openCloseBranch(branchID);
    }

    private void displayCustomerInterface() {
        // Display customer interface options
        // E.g., browse menu, place an order, etc.
    }
}
