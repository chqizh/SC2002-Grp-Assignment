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
    private transient Scanner sc;
    private InMemoryDatabase db;

    public FOMSApp() {
        // Initialize scanner here to prevent resource leak
        sc = new Scanner(System.in);
        // Deserialization
        try {
            db = (InMemoryDatabase) SerializationUtil.deserialize(DATA_STORE);
            System.out.println("reading from DATA_STORE");
        } catch (IOException | ClassNotFoundException e) {
            db = new InMemoryDatabase(); // Create a new one if no data is found
        }
    }

    public static void main(String[] args) {
        FOMSApp app = new FOMSApp();
        // app.db.addAdmin(new Admin("Kurt","KurtA",'M',40, app.db));
        // app.db.addAccount(new Account("KurtA"));
        // app.db.addAdmin(new Admin("Henry", "HenryT", 'M', 60,app.db));
        // app.db.addAccount(new Account("HenryT"));
        app.run();
        app.sc.close();
    }

    public void run() {
        System.out.println(ANSI_CYAN + "Welcome to the Fastfood Ordering Management System." + ANSI_RESET);
        System.out.println("Are you a customer? (Y/N)");
        Console console = System.console();
        String userType = console.readLine();
        if ("Y".equalsIgnoreCase(userType)) {
            displayCustomerInterface();
        } else {
            Employee employee = null;
            while (employee == null) {
                String staffID = console.readLine("Enter your staffID: ");
                char[] passwordArray = console.readPassword("Enter your password: "); // Password will be masked
                String password = new String(passwordArray);
    
                employee = db.validateEmployee(staffID, password);
                if (employee == null) {
                    System.out.println("Login unsuccessful. Please try again.");
                    java.util.Arrays.fill(passwordArray, ' ');
                }
            }

            if (employee instanceof Staff) {
                displayStaffInterface((Staff) employee);
            } else if (employee instanceof BranchManager) {
                displayBranchManagerInterface((BranchManager) employee);
            } else if (employee instanceof Admin) {
                displayAdminInterface((Admin) employee);
            }
        }

        // Serialization
        try
        {
            SerializationUtil.serialize(db, DATA_STORE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayStaffInterface(Staff staff) {
        boolean keepRunning = true;
        Branch branch = db.getBranchByBranchName(staff.getBranchName());

        while (keepRunning) {
            System.out.println("\n|| Welcome to STAFF Workspace || ");
            System.out.println("(1) Display New Orders ");
            System.out.println("(2) View Order Details ");
            System.out.println("(3) Process Order ");
            System.out.println("Please select your action: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    staff.viewNewOrders(branch);
                    break;
                case 2:
                    System.out.println("Enter the order ID: ");
                    int orderID = sc.nextInt();
                    sc.nextLine();
                    staff.viewOrder(branch,orderID);
                    break;
                case 3:
                    System.out.println("Enter the order ID to be processed: ");
                    int orderID_P = sc.nextInt();
                    sc.nextLine();
                    staff.processOrders(branch, orderID_P);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }

    private void displayBranchManagerInterface(BranchManager manager) {
        Branch branch = db.getBranchByBranchName(manager.getBranchName());
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n|| Welcome to the BRANCH MANAGER Workspace ||");
            System.out.println("Please select your action:");
            System.out.println("(1) Display New Orders");
            System.out.println("(2) View Order Details");
            System.out.println("(3) Process Order");
            System.out.println("(4) Display Staff List");
            System.out.println("(5) Add Menu Item");
            System.out.println("(6) Edit Menu Item");
            System.out.println("(7) Remove Menu Item");
            System.out.println("(8) Log Out");
            System.out.println("Please select your action: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    manager.viewNewOrders(branch);
                    break;
                case 2:
                    System.out.println("Enter order ID: ");
                    int orderID = sc.nextInt();
                    sc.nextLine(); // Consume the newline
                    manager.viewOrder(branch,orderID);
                    break;
                case 3:
                    System.out.println("Enter order ID to process: ");
                    int orderIdToProcess = sc.nextInt();
                    sc.nextLine(); // Consume the newline
                    manager.processOrders(branch,orderIdToProcess);
                    break;
                case 4:
                    manager.displayStaffList();
                    break;
                case 5:
                    manager.addMenuItem(branch);
                    break;
                case 6:
                    manager.editMenuItem(branch);
                    break;
                case 7:
                    manager.removeMenuItem(branch);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private void displayAdminInterface(Admin admin) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n|| Welcome to the ADMIN Workspace ||");
            System.out.println("(1) Add Staff");
            System.out.println("(2) Remove Staff");
            System.out.println("(3) Edit Staff");
            System.out.println("(4) Display Staff List");
            System.out.println("(5) Assign Managers");
            System.out.println("(6) Promote Staff to Manager");
            System.out.println("(7) Transfer Staff/Manager");
            System.out.println("(8) Edit Payment Method");
            System.out.println("(9) Open Branch");
            System.out.println("(10) Close Branch");
            System.out.println("(11) Log Out");

            System.out.println("Please select your action: ");
        
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    System.out.println("Enter a name.");
                    String name = sc.nextLine();
                    System.out.println("Enter a staffID.");
                    String staffID = sc.nextLine();
                    System.out.println("Enter a gender (M/F/N).");
                    char gender = sc.nextLine().charAt(0);
                    System.out.println("Enter a age.");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter a branch.");
                    String branchName = sc.next();
                    admin.addStaff(name, staffID, gender, age, branchName);
                    System.out.println("Success.");
                    break;
                case 2:
                    System.out.println("Enter a staffID.");
                    String staffID2 = sc.next();
                    admin.removeStaff(staffID2);
                    break;
                case 3:
                    String staffID3 = sc.next();
                    admin.editStaff(staffID3);
                    break;
                case 4:
                    admin.displayStaffList();
                    break;
                case 5:
                    //admin.assignManagers();
                    break;
                case 6:
                    //admin.promoteStaff();
                    break;
                case 7:
                    //admin.transferStaff();
                    break;
                case 8:
                    //admin.editPaymentMethod();
                    break;
                case 9:
                    admin.addBranch();
                    break;
                case 10:
                    //admin.removeBranch(branchName);
                    break;
                case 11:
                    System.out.println("Logging out...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
    
    //private void displayCustomerInterface() throws IOException {
    private void displayCustomerInterface() {

        boolean keepRunning = true;
        Customer customer = new Customer();
        System.out.println("Enter Branch Name:");
        String branchName = sc.next();
        sc.nextLine();
        Branch branch = db.getBranchByBranchName(branchName);

        while(branch == null){
            System.out.println("You have entered an invalid Branch ID!");
            System.out.println("Enter Branch ID:");
            branchName = sc.next();
            sc.nextLine();
            branch = db.getBranchByBranchName(branchName);
        }

        while (keepRunning) {
            System.out.println("/n|| Welcome to MadDonkeys! ||");
            System.out.println("(1) Browse Menu");
            System.out.println("(2) Add to Cart");
            System.out.println("(3) Delete from Cart");
            System.out.println("(4) View Cart");
            System.out.println("(5) Place Order");
            System.out.println("(6) Track Order");
            System.out.println("(7) Exit System");
            System.out.println("Please select your action:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                customer.browseMenu();
                    break;
                case 2:
                customer.addToCart();
                    break;
                case 3:
                customer.deleteFromCart();
                    break;
                case 4:
                customer.viewCart();
                    break;
                case 5:
                customer.placeOrder(branchName);
                    break;
                case 6:
                customer.trackOrder(branchName);
                    break;
                case 7:
                    System.out.println("Exiting customer interface...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}
