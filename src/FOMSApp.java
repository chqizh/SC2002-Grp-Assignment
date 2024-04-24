import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

import Accounts.*;
import Branch.*;
import Customer.*;
import Database.*;
import DataPersistence.*;

public class FOMSApp implements Serializable{
    private static String filePath = new File("").getAbsolutePath();
    private static final String DATA_STORE = "Data/data_store.ser";
    private transient Scanner sc;
    private InMemoryDatabase db;

    public FOMSApp() {
        // Initialize scanner here to prevent resource leak
        sc = new Scanner(System.in);
        // Deserialization
        try {
            db = (InMemoryDatabase) SerializationUtil.deserialize(DATA_STORE);
            System.out.println("Reading from DATA_STORE");
        } catch (IOException | ClassNotFoundException e) {
            db = new InMemoryDatabase(); // Create a new one if no data is found
        }
    }

    public static void main(String[] args) {
        FOMSApp app = new FOMSApp();
        printFOMSTitle();
        
        Scanner sc = new Scanner(System.in);
        app.branchInitialization();
        app.staffInitialization();
        app.menuInitialization();

        app.run();
        sc.close();
    }

    public void branchInitialization() {
        this.sc = new Scanner(System.in);
        if (Files.exists(Paths.get(filePath.concat("/Data/branch_list.csv")))) {
            System.out.print("branch_list.csv file was found. Initialize using branch_list.csv (Y/N)? ");
            String choice = sc.nextLine().toUpperCase(); // Convert input to uppercase
            if (choice.equalsIgnoreCase("Y")) {
                DatabaseInitializer initializer = new DatabaseInitializer(this.db);
                initializer.initializeBranchList(filePath.concat("/Data/branch_list.csv"));
            } else if (choice.equalsIgnoreCase("N")) {
                // Do nothing
            } else {
                System.out.println("Invalid choice entered. branch_list.csv will not be imported.");
            }
        } else {
            System.out.println("Branch list not found");
        }
    }

    public void staffInitialization() {
        this.sc = new Scanner(System.in);
        if (Files.exists(Paths.get(filePath.concat("/Data/staff_list.csv")))) {
            System.out.print("staff_list.csv file was found. Initialize using staff_list.csv (Y/N)? ");
            String choice = sc.nextLine().toUpperCase(); // Convert input to uppercase
            if (choice.equalsIgnoreCase("Y")) {
                DatabaseInitializer initializer = new DatabaseInitializer(this.db);
                initializer.initializeStaffList(filePath.concat("/Data/staff_list.csv"));
            } else if (choice.equalsIgnoreCase("N")) {
                // Do nothing
            } else {
                System.out.println("Invalid choice entered. staff_list.csv will not be imported.");
            }
        } else {
            System.out.println("Staff list not found");
        }
    }

    public void menuInitialization() {
        this.sc = new Scanner(System.in);
        if (Files.exists(Paths.get(filePath.concat("/Data/menu_list.csv")))) {
            System.out.print("menu_list.csv file was found. Initialize using menu_list.csv (Y/N)? ");
            String choice = sc.nextLine().toUpperCase(); // Convert input to uppercase
            if (choice.equalsIgnoreCase("Y")) {
                DatabaseInitializer initializer = new DatabaseInitializer(this.db);
                initializer.initializeMenuList(filePath.concat("/Data/menu_list.csv"));
            } else if (choice.equalsIgnoreCase("N")) {
                // Do nothing
            } else {
                System.out.println("Invalid choice entered. menu_list.csv will not be imported.");
            }
        } else {
            System.out.println("Menu list not found");
        }
    }

    public void run() {
        Console console = System.console();
        String userType = console.readLine("Are you a customer? (Y/N): ");
        if ("Y".equalsIgnoreCase(userType)) {
            displayCustomerInterface(new Customer(db));
        } else {
            boolean loginSuccessful = false;
            Account account = null;
            while (!loginSuccessful) {
                String staffID = console.readLine("Enter your staffID: ");
                account = db.getAccountByStaffID(staffID);
                if (account == null) {
                    System.out.println("Staff ID not found. Please try again.");
                    continue;
                }
    
                char[] passwordArray = console.readPassword("Enter your password: "); // Password will be masked
                String password = new String(passwordArray);
                
                if (account.validateLogin(password)) {
                    if (account.isUsingDefaultPassword()){
                        System.out.println("The default password is currently in use. Please change your password.");
                        account.changePassword(console);
                    };
                    loginSuccessful = true;
                } else {
                    System.out.println("Login unsuccessful. Please try again.");
                }
                
                java.util.Arrays.fill(passwordArray, ' ');
            }
    
            // Continue with logged-in user
            Employee employee = db.getEmployee(account.getStaffID());
            if (employee != null) {
                if (employee instanceof Staff) {
                    displayStaffInterface((Staff) employee);
                } else if (employee instanceof BranchManager) {
                    displayBranchManagerInterface((BranchManager) employee);
                } else if (employee instanceof Admin) {
                    displayAdminInterface((Admin) employee);
                }
            } else {
                System.out.println("An error occurred retrieving employee information.");
            }
        }
        // Serialization
        try
        {
            SerializationUtil.serialize(db, DATA_STORE);
            // System.out.println("successfully serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayStaffInterface(Staff staff) {
        Console console = System.console();
        boolean keepRunning = true;
        Branch branch = db.getBranchByBranchName(staff.getBranchName());

        while (keepRunning) {
            System.out.println("\nUser: " + staff.getName() + "\nBranch: " + branch.getBranchName());
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║  Welcome to the STAFF Workspace.  ║");
            System.out.println("╠═══════════════════════════════════╣");
            System.out.println("║ (1) Display New Orders            ║");
            System.out.println("║ (2) View Order Details            ║");
            System.out.println("║ (3) Process Order                 ║");
            System.out.println("║ (4) Change Password               ║");
            System.out.println("║ (5) Log Out                       ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.print("Please select your action: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    staff.viewNewOrders(branch);
                    break;
                case 2:
                    System.out.print("Enter the order ID: ");
                    int orderID = sc.nextInt();
                    sc.nextLine();
                    staff.viewOrder(branch,orderID);
                    break;
                case 3:
                    System.out.print("Enter the order ID to be processed: ");
                    int orderID_P = sc.nextInt();
                    sc.nextLine();
                    staff.processOrders(branch, orderID_P);
                    break;
                case 4:
                    Account account = db.getAccountByStaffID(staff.getStaffID());
                    account.changePassword(console);
                    break;
                case 5:
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
            System.out.println("\nUser: " + manager.getName() + "\nBranch: " + branch.getBranchName());
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║  Welcome to the BRANCH MANAGER Workspace.  ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ (1) Display New Orders                     ║");
            System.out.println("║ (2) View Order Details                     ║");
            System.out.println("║ (3) Process Order                          ║");
            System.out.println("║ (4) Display Staff List                     ║");
            System.out.println("║ (5) View Branch Menu                       ║");
            System.out.println("║ (6) Add Menu Item                          ║");
            System.out.println("║ (7) Edit Menu Item                         ║");
            System.out.println("║ (8) Remove Menu Item                       ║");
            System.out.println("║ (9) Log Out                                ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Please select your action: ");
            sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    manager.viewNewOrders(branch);
                    break;
                case 2:
                    System.out.println("Enter order ID: ");
                    int orderID = sc.nextInt();
                    sc.nextLine();
                    manager.viewOrder(branch,orderID);
                    break;
                case 3:
                    System.out.println("Enter order ID to process: ");
                    int orderIdToProcess = sc.nextInt();
                    sc.nextLine();
                    manager.processOrders(branch,orderIdToProcess);
                    break;
                case 4:
                    manager.displayStaffList(true);
                    break;
                case 5:
                    manager.viewMenu(branch);
                    break;
                case 6:
                    manager.addMenuItem(branch);
                    break;
                case 7:
                    manager.editMenuItem(branch);
                    break;
                case 8:
                    manager.removeMenuItem(branch);
                    break;
                case 9:
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
            System.out.println("\nUser: " + admin.getName());
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║  Welcome to the ADMIN Workspace.  ║");
            System.out.println("╠═══════════════════════════════════╣");
            System.out.println("║ (1)  Add Staff                    ║");
            System.out.println("║ (2)  Remove Staff                 ║");
            System.out.println("║ (3)  Edit Staff                   ║");
            System.out.println("║ (4)  Display Staff List           ║");
            System.out.println("║ (5)  Add Manager                  ║");
            System.out.println("║ (7)  Promote Staff to Manager     ║");
            System.out.println("║ (8)  Transfer Staff/Manager       ║");
            System.out.println("║ (9)  Edit Payment Method          ║");
            System.out.println("║ (10) Open Branch                  ║");
            System.out.println("║ (11) Close Branch                 ║");
            System.out.println("║ (12) Export Staff List            ║");
            System.out.println("║ (13) Log Out                      ║");
            System.out.println("╚═══════════════════════════════════╝");

            System.out.print("Please select your action: ");
            sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    if (admin.addStaff()) System.out.println("Successfully added new staff.");
                    else System.out.println("Failed to add staff.");
                    break;
                case 2:
                    if (admin.removeStaff()) System.out.println("Successfully removed staff.");
                    else System.out.println("Failed to remove staff.");
                    break;
                case 3:
                    if (admin.editStaff()) System.out.println("Successfully edited staff.");
                    else System.out.println("Failed to edit staff.");
                    break;
                case 4:
                    admin.displayStaffList(false);
                    break;
                case 5:
                    if (admin.addManager()) System.out.println("Successfully assigned manager.");
                    else System.out.println("Failed to assign manager.");
                    break;
                case 6:
                    if (admin.removeManager()) System.out.println("Successfully removed manager.");
                    else System.out.println("Failed to remove manager. ");
                    break;
                case 7:
                    if (admin.promoteStaff()) System.out.println("Successfully promoted staff.");
                    else System.out.println("Failed to promote staff.");
                    break;
                case 8:
                    if (admin.transferEmployee()) System.out.println("Successfully transferred employee.");
                    else System.out.println("Failed to transfer employee.");
                    break;
                case 9:
                    if (admin.editPaymentMethod()) System.out.println("Successfully edited payment method.");
                    else System.out.println("Failed to edit payment method.");
                    break;
                case 10:
                    if (admin.addBranch()) System.out.println("Successfully added branch.");
                    else System.out.println("Failed to add branch.");
                    break;
                case 11:
                    if (admin.removeBranch()) System.out.println("Successfully removed branch.");
                    else System.out.println("Failed to remove branch.");
                    break;
                case 12:
                    try {
                        String csvFilePath = filePath.concat("/Data/staff_list_export.csv");
                        DatabaseExporter exporter = new DatabaseExporter(db);
                        exporter.exportDataToCSV(csvFilePath); // This could throw an IOException
                        System.out.println("Data export to CSV completed successfully.");
                    } catch (IOException e) {
                        System.err.println("An error occurred during data export: " + e.getMessage());
                        e.printStackTrace();
                    }        
                    break;
                case 13:
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
    private void displayCustomerInterface(Customer customer) {
        boolean keepRunning = true;
    
        ArrayList<String> branchNames = db.getAllBranchNames();
        System.out.println("Please select a Branch.");
        int i = 1;
        Map<Integer, String> branchNumberMap = new HashMap<>();
        for (String branchName : branchNames) {
            System.out.printf("(%d) %s \n", i, branchName);
            branchNumberMap.put(i, branchName); // Map the number to branch name
            i++;
        }
    
        Branch branch = null;
        String branchName;
        do {
            System.out.print("Enter Branch Number:");
            int branchNumber = Integer.parseInt(sc.nextLine());
            branchName = branchNumberMap.get(branchNumber); // Get branch name using the number
            if (branchName == null) {
                System.out.println("You have entered an invalid Branch Number!");
            } else {
                branch = db.getBranchByBranchName(branchName);
                if (branch == null) {
                    System.out.println("You have entered an invalid Branch Name!");
                }
            }
        } while (branch == null);

        while (keepRunning) {
            System.out.println("\nBranch: " + branch.getBranchName());
            System.out.println("╔══════════════════════════╗");
            System.out.println("║  Welcome to MadDonkeys!  ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║ (1) Browse Menu          ║");
            System.out.println("║ (2) Add to Cart          ║");
            System.out.println("║ (3) Delete from Cart     ║"); 
            System.out.println("║ (4) View Cart            ║");
            System.out.println("║ (5) Place Order          ║");
            System.out.println("║ (6) Track Order          ║");
            System.out.println("║ (7) Collect Order        ║");
            System.out.println("║ (8) Exit System          ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Please select your action: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    customer.browseMenu(branchName);
                    break;
                case 2:
                    customer.addToCart(branchName);
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
                    customer.collectOrder(branchName);
                    break;
                case 8:
                    System.out.println("Exiting customer interface...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }

    private static void printFOMSTitle() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                   ║");
        System.out.println("║                                                                                                   ║");
        System.out.println("║                             ███████╗   ██████╗   ███╗   ███╗  ███████╗                            ║");
        System.out.println("║                             ██╔════╝  ██╔═══██╗  ████╗ ████║  ██╔════╝                            ║");
        System.out.println("║                             █████╗    ██║   ██║  ██╔████╔██║  ███████╗                            ║");
        System.out.println("║                             ██╔══╝    ██║   ██║  ██║╚██╔╝██║  ╚════██║                            ║");
        System.out.println("║                             ██║       ╚██████╔╝  ██║ ╚═╝ ██║  ███████║                            ║");
        System.out.println("║                             ╚═╝        ╚═════╝   ╚═╝     ╚═╝  ╚══════╝                            ║");
        System.out.println("║                                                                                                   ║");
        System.out.println("║                       Welcome to the Fastfood Ordering and Management System!                     ║");
        System.out.println("║                                            FDAA Grp 1                                             ║");
        System.out.println("║                                                                                                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
}