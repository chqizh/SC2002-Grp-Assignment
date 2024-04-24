package Accounts;

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.List; 

import Branch.*;
import Customer.*;
import Database.InMemoryDatabase;
/**
 * Represents an administrator with specific management privileges.
 * Inherits from Employee and implements interfaces for admin and staff management.
 */
public class Admin extends Employee implements IAdminManagement, IStaffManagement {
    private transient Scanner sc;
    /**
     * Constructs an Admin with specified details and initializes the database.
     *
     * @param name  The name of the admin.
     * @param staffID The unique ID of the admin.
     * @param gender The gender of the admin.
     * @param age The age of the admin.
     * @param db The database connection for the admin operations.
     */
    public Admin(String name, String staffID, char gender, int age, InMemoryDatabase db){
        super(name, staffID, UserType.ADMIN, gender, age, db);
    }
    /**
     * Gets the name of the branch associated with the admin.
     * This implementation always returns an empty string as admins do not have an associated branch.
     *
     * @return The branch name, which is always an empty string.
     */
    public String getBranchName(){
        return "";
    }

    /**
     * Adds a new staff member to the system after collecting their information.
     *
     * @return true if staff was added successfully, false otherwise.
     */
    public boolean addStaff(){
        sc = new Scanner(System.in);
        try {
            System.out.print("Enter their name: ");
            String name = sc.nextLine();
            System.out.print("Enter their Staff ID: ");
            String staffID = sc.nextLine();
            System.out.print("Enter their gender (M/F): ");
            char gender = sc.nextLine().charAt(0);
            System.out.print("Enter their age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter their assigned Branch: ");
            String branchName = sc.nextLine();
            if (db.getBranchByBranchName(branchName) == null) {
                System.out.println("Branch does not exist.");
                return false;
            }
            Staff newStaff = new Staff(name, staffID, gender, age, branchName, this.db);
            Account newAccount = new Account(staffID);
            if (db.addStaff(newStaff)){
                db.addAccount(newAccount);
                return true;
            }
            else return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    };

    /**
     * Removes a staff member from the system based on their staff ID.
     *
     * @return true if the staff was removed successfully, false otherwise.
     */
    public boolean removeStaff(){
        System.out.print("Enter a staffID to remove: ");
        displayStaffList(); // Find someway to disply all instead of filtering
        String staffID = sc.nextLine();
        if (db.getAccountByStaffID(staffID) != null){
            db.removeStaff(staffID);
            return true;
        }
        else return false;
    };

    /**
     * Edits the information of an existing staff member.
     *
     * @return true if the staff was edited successfully, false otherwise.
     */
    public boolean editStaff(){
        sc = new Scanner(System.in);
        displayStaffList();
        System.out.print("Enter the staffID would you like to edit: ");
        String staffID = sc.nextLine();
        Staff staff = db.getStaff(staffID);
        if (staff == null) return false;
        System.out.print("What would you like to edit? Enter the numerical option: ");
        System.out.println("(1) Name");
        System.out.println("(2) Gender");
        System.out.println("(3) Age");
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.print("Please enter a new name: ");
                    String name = sc.nextLine();
                    staff.setName(name);
                    break;
                case 2:
                    System.out.print("Please enter a new gender: ");
                    char gender = sc.nextLine().charAt(0);
                    staff.setGender(gender);
                    break;
                case 3:
                    System.out.print("Please enter a new age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    staff.setAge(age);
                    break;
                default:
                    System.out.println("Invalid integer entered.");
                    break;
            }
        }
        catch (Exception e){
            System.out.println("Invalid integer entered.");
            return false;
        }
        return true;
    };

    /**
     * Adds a manager to a branch.
     *
     * @return true if the manager was added successfully, false otherwise.
     */
    public boolean addManager(){
        sc = new Scanner(System.in);
        try {
            System.out.print("Enter their name: ");
            String name = sc.nextLine();
            System.out.print("Enter their Staff ID: ");
            String staffID = sc.nextLine();
            System.out.print("Enter their gender (M/F): ");
            char gender = sc.nextLine().charAt(0);
            System.out.print("Enter their age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter their assigned Branch: ");
            String branchName = sc.nextLine();

            if (db.getBranchByBranchName(branchName) == null) {
                System.out.println("Branch does not exist.");
                return false;
            }

            BranchManager newBranchManager = new BranchManager(name, staffID, gender, age, branchName, this.db);
            Account newAccount = new Account(staffID);
            if (db.addBranchManager(newBranchManager)){
                db.addAccount(newAccount);
                System.out.printf("Manager %s (%s) assigned to %s.", name, staffID, branchName);
                return true;
            }
            else return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    };

    /**
     * Promotes an existing staff member to a branch manager.
     *
     * @return true if the staff was promoted successfully, false otherwise.
     */
    public boolean promoteStaff(){
        sc = new Scanner(System.in);
        System.out.print("Enter staffID to promote: ");
        String staffID = sc.nextLine();
        if (db.getEmployee(staffID) == null){
            System.out.print("Employee with chosen staffID does not exist. ");
            return false;
        }
        if (db.getEmployee(staffID).getUserType() == UserType.STAFF){
            Staff staff = db.getStaff(staffID);
            BranchManager newBranchManager = new BranchManager(staff.getName(), staffID, staff.getGender(), staff.getAge(), staff.getBranchName(), db);
            if (db.addBranchManager(newBranchManager)){
                db.removeStaff(staffID);
                return true;
            }
            else return false;
        }
        else {
            System.out.print("Employee with chosen staffID is a Branch Manager or Admin. ");
            return false;
        }
    }

    /**
     * Transfers an employee from one branch to another.
     *
     * @return true if the transfer was successful, false otherwise.
     */
    public boolean transferEmployee(){
        sc = new Scanner(System.in);
        System.out.print("Enter employee's staffID: ");
        String staffID = sc.nextLine();
        System.out.print("Enter employee's new Branch: ");
        String branchName = sc.nextLine();
        if (db.getEmployee(staffID) == null ) {
            System.out.print("Staff/Manager does not exist.");
            return false;
        }
        else if (db.getBranchByBranchName(branchName) == null) {
            System.out.print("Branch does not exist.");
            return false;
        }
        
        if (db.getEmployee(staffID).getUserType() == UserType.STAFF){
            Staff staff = db.getStaff(staffID);
            String oldBranchName = staff.getBranchName();
            // Removes staff from their old branch.
            if (db.getBranchByBranchName(oldBranchName).removeStaff(staffID) == false){
                System.out.printf("Failed to remove %s from old branch %s. ", staffID, oldBranchName);
                return false;
            }
            // Transfers staff to new branch.
            else if (db.getBranchByBranchName(branchName).addStaff(staffID)){
                staff.setBranchName(branchName);
                System.out.printf("%s transferred to new branch %s. ", staffID, branchName);
                return true;
            }
            else return false;
        }
        else if (db.getEmployee(staffID).getUserType() == UserType.BRANCH_MANAGER){
            BranchManager manager = db.getBranchManager(staffID);
            String oldBranchName = manager.getBranchName();

            if (db.getBranchByBranchName(oldBranchName).removeBranchManager(staffID) ==  false){
                System.out.printf("Failed to remove %s from old branch %s.", staffID, oldBranchName);
                return false;
            }
            else if (db.getBranchByBranchName(branchName).addBranchManager(staffID)){
                manager.setBranchName(branchName);
                System.out.printf("%s transferred to new branch %s. ", staffID, branchName);
                return true;
            }
            else return false;
        }
        else if (db.getEmployee(staffID).getUserType() == UserType.ADMIN){
            System.out.print("Employee is an Admin.");
            return false;
        }
        else return false;
    };

    /**
     * Edits the payment methods available in the system.
     *
     * @return true if the payment method was edited successfully, false otherwise.
     */
    public boolean editPaymentMethod(){
        sc = new Scanner(System.in);
        List<Payment> paymentMethods = new ArrayList<>(db.getPaymentMethods());
        
        System.out.println("Enter which payment method you would like to enable/disable:");
        int i = 0;
        for (Payment paymentMethod : paymentMethods){
            i++;
            if (db.getPaymentMethodsStatus(paymentMethod)) System.out.println("(" + i + ") " + paymentMethod.getPaymentMethodName() + ": Enabled");
            else System.out.println("(" + i + ") " + paymentMethod.getPaymentMethodName() + ": Disabled");
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return db.togglePaymentMethod(paymentMethods.get(choice));
    }

    /**
     * Adds a new branch to the system.
     *
     * @return true if the branch was added successfully, false otherwise.
     */
    public boolean addBranch(){
        try {
            sc = new Scanner(System.in);
            System.out.print("Enter branch name: ");
            String branchName = sc.nextLine();
            System.out.print("Enter branch location: ");
            String branchLocation = sc.nextLine();
            System.out.print("Enter staff quota: ");
            int staffQuota = sc.nextInt();
            sc.nextLine();
    
            Branch newBranch = new Branch(branchName, branchLocation, staffQuota); 
            if (db.addBranch(newBranch)){
                return true;
            }
            else return false;
        }
        catch (Exception e){
            System.out.print("Invalid input entered.");
            e.printStackTrace();
            return false;
        }
        
    };

    /**
     * Removes a branch from the system.
     *
     * @return true if the branch was removed successfully, false otherwise.
     */
    public boolean removeBranch(){
        displayAllBranches();
        sc = new Scanner(System.in);
        System.out.print("Enter branch name: ");
        String branchName = sc.nextLine();
        if (db.removeBranch(branchName)) return true;
        else return false;
    };

    /**
     * Displays all branches in the system.
     */
    public void displayAllBranches(){
        ArrayList<String> branchesList = db.getAllBranchNames();
        for (String branchName : branchesList){
            System.out.printf(" - %s\n", branchName);
        }
    }

    /**
     * Displays a list of staff members with filtering options.
     */
    public void displayStaffList(){
        sc = new Scanner(System.in);
        System.out.println("Choose filter for staff list display: ");
        System.out.println("(1) Display all");
        System.out.println("(2) Filter by Branch");
        System.out.println("(3) Filter by Role");
        System.out.println("(4) Filter by Gender");
        System.out.println("(5) Filter by Age");
        int choice = sc.nextInt();
        sc.nextLine();

        ArrayList<String> employeesIDsList = db.getAllEmployeeIDs();
        ArrayList<Employee> employeesList = new ArrayList<>();
        for (String employeeID : employeesIDsList) {
            Employee employee = db.getEmployee(employeeID);
            if (employee != null) employeesList.add(employee);
        }

        switch(choice){
            case 1:
                System.out.println("Displaying all.");
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
                System.out.println("----------------------------------------------------------------------");
                for (Employee employee : employeesList){
                    System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.print("Press any key to continue.");
                sc.nextLine();
                break;
            case 2: 
                System.out.print("Filter by Branch: ");
                String branch = sc.nextLine();
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
                System.out.println("----------------------------------------------------------------------");
                for (Employee employee : employeesList){
                    if (employee.getBranchName().equals(branch)){
                        System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
                    }
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Press any key to continue.");
                sc.nextLine();
                break;
            case 3:
                System.out.print("Filter by role (S/M/A): ");
                char roleInput = sc.nextLine().charAt(0);
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
                System.out.println("----------------------------------------------------------------------");
                try {
                    UserType role = UserType.fromCode(roleInput);
                    for (Employee employee : employeesList){
                        if (employee.getUserType() == role){
                            System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid role chosen.");
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Press any key to continue.");
                sc.nextLine();
                break;
            case 4: 
                System.out.print("Filter by gender (M/F): ");
                char gender = sc.nextLine().charAt(0);
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
                System.out.println("----------------------------------------------------------------------");
                for (Employee employee : employeesList){
                    if (employee.getGender() == gender){
                        System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
                    }
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Press any key to continue.");
                sc.nextLine();
                break;
            case 5:
                System.out.print("Filter by age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.println("----------------------------------------------------------------------");
                System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", "Name", "staffID", "Role", "Gender", "Age", "Branch");
                System.out.println("----------------------------------------------------------------------");
                for (Employee employee : employeesList){
                    if (employee.getAge() == age){
                        System.out.printf("%-20s %-10s %-16s %-8s %-5s %-15s\n", employee.getName(), employee.getStaffID(), employee.getUserType().stringFromUserType(), employee.getGender(), employee.getAge(), employee.getBranchName());
                    }
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Press any key to continue.");
                sc.nextLine();
                break;
            default:
                break;
        }
    }
}