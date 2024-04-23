package Accounts;

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.List; 

import Branch.*;
import Customer.*;
import Database.InMemoryDatabase;


public class Admin extends Employee implements IAdminManagement, IStaffManagement {
    private transient Scanner sc;

    public Admin(String name, String staffID, char gender, int age, InMemoryDatabase db){
        super(name, staffID, UserType.ADMIN, gender, age, db);
    }
    
    public String getBranchName(){
        return "";
    }

    // From IAdminManagement
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
            db.addStaff(newStaff);
            db.addAccount(newAccount);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    };

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

    public boolean assignManager(){
        sc = new Scanner(System.in);
        System.out.print("Enter Branch to assign Manager to: ");
        String branchName = sc.nextLine();
        System.out.print("Enter staffID of Manager: ");
        String staffID = sc.nextLine();
        
        if (db.getStaff(staffID) == null) {
            System.out.println("Manager does not exist.");
            return false;
        }
        else if (db.getBranchByBranchName(branchName) == null) {
            System.out.println("Branch does not exist.");
            return false;
        }
        else {
            db.getBranchByBranchName(branchName).addBranchManager(staffID);
            System.out.printf("%s assigned to %s.", staffID, branchName);
            return true;
        }
    };

    public boolean promoteStaff(){
        sc = new Scanner(System.in);
        System.out.print("Enter staffID to promote: ");
        String staffID = sc.nextLine();
        if (db.getEmployee(staffID).getUserType() == UserType.STAFF){
            Staff staff = db.getStaff(staffID);
            BranchManager newBranchManager = new BranchManager(staff.getName(), staffID, staff.getGender(), staff.getAge(), staff.getBranchName(), db);
            db.addBranchManager(newBranchManager);
            db.removeStaff(staffID);
            return true;
        }
        else {
            System.out.println("Employee with chosen staffID is already a Branch Manager.");
            return false;
        }
    }

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
                System.out.printf("Failed to remove %s from old branch %s.", staffID, oldBranchName);
                return false;
            }
            // Transfers staff to new branch.
            else if (db.getBranchByBranchName(branchName).addStaff(staffID)){
                staff.setBranchName(branchName);
                System.out.printf("%s transferred to new branch %s", staffID, branchName);
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

    public boolean editPaymentMethod(){
        sc = new Scanner(System.in);
        List<Payment> paymentMethods = new ArrayList<>(db.getPaymentMethods());
        
        System.out.println("Enter which payment method you would like to enable/disable:");
        int i = 0;
        for (Payment paymentMethod : paymentMethods){
            if (db.getPaymentMethodsStatus(paymentMethod)) System.out.println("(" + i + ") " + paymentMethod.getPaymentMethodName() + ": Enabled");
            else System.out.println("(" + i + ") " + paymentMethod.getPaymentMethodName() + ": Disabled");
            i++;
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return db.togglePaymentMethod(paymentMethods.get(choice));
    }

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

    public boolean removeBranch(){
        displayAllBranches();
        sc = new Scanner(System.in);
        System.out.print("Enter branch name: ");
        String branchName = sc.nextLine();
        if (db.removeBranch(branchName)) return true;
        else return false;
    };

    public void displayAllBranches(){
        ArrayList<String> branchesList = db.getAllBranchNames();
        int i = 0;
        for (String branchName : branchesList){
            i++;
            System.out.printf("(%i) %s", i, branchName);
        }
    }

    // From IStaffManagement
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