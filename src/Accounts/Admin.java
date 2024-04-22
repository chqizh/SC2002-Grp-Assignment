package Accounts;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner; 

import Branch.*;
import Database.InMemoryDatabase;


public class Admin extends Employee implements IAdminManagement, IStaffManagement {
    private transient Scanner sc;

    public Admin(String name, String staffID, char gender, int age, InMemoryDatabase db){
        super(name, staffID, UserType.ADMIN, gender, age, db);
    }

    // From IAdminManagement
    public boolean addStaff(){
        sc = new Scanner(System.in);
        try {
            System.out.print("Enter their name: ");
            String name = sc.nextLine();
            System.out.print("Enter their Staff ID: ");
            String staffID = sc.nextLine();
            System.out.print("Enter their gender (M/F/N): ");
            char gender = sc.nextLine().charAt(0);
            System.out.print("Enter their age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter their assigned Branch: ");
            String branchName = sc.nextLine();
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
                    String name = sc.nexLine();
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

    public void assignManager(){
        sc = new Scanner(System.in);
        System.out.print("Enter Branch to assign Manager to: ");
        String branchName = sc.nextLine();
        System.out.print("Enter staffID of Manager: ");
        String staffID = sc.nextLine();
        
        if (db.getStaff(staffID) == null) {
            System.out.println("Manager does not exist. Manager assignment unsuccessful.");
        }
        else if (db.getBranchByBranchName(branchName) == null) {
            System.out.println("Branch does not exist. Manager assignment unsuccessful.");
        }
        else {
            db.getBranchByBranchName(branchName).addBranchManager(staffID);
            System.out.printf("Successfully assigned Branch Manager (%s) to Branch (%s).", staffID, branchName);
        }
    };

    public void promoteStaff(){
        ;
    }

    public void transferEmployee(){
        sc = new Scanner(System.in);
        System.out.print("Enter employee's staffID: ");
        String staffID = sc.nextLine();
        System.out.print("Enter employee's new Branch: ");
        String branchName = sc.nextLine();
        if (db.getStaff(staffID) == null) {
            System.out.println("Staff/Manager does not exist. Employee assignment unsuccessful.");
        }
        else if (db.getBranchByBranchName(branchName) == null) {
            System.out.println("Branch does not exist. Employee assignment unsuccessful.");
        }
        
        if (db.getStaff(staffID).getUserType() == UserType.STAFF){
            Staff staff = db.getStaff(staffID);
            String oldBranchName = staff.getBranchName();
            // Removes staff from their old branch.
            if (db.getBranchByBranchName(oldBranchName).removeStaff(staffID) == false){
                System.out.println("Transfer unsuccessful.");
                return;
            }
            // Transfers staff to new branch.
            else if (db.getBranchByBranchName(branchName).addStaff(staffID)){
                staff.setBranchName(branchName);
                System.out.printf("Successfully transferred Staff (%s) to Branch (%s)", staffID, branchName);
            }
            else System.out.println("Transfer unsuccessful.");
        }
        else if (db.getStaff(staffID).getUserType() == UserType.BRANCH_MANAGER){
            BranchManager manager = db.getBranchManager(staffID);
            String oldBranchName = manager.getBranchName();

            if (db.getBranchByBranchName(oldBranchName).removeBranchManager(staffID) ==  false){
                System.out.println("Transfer unsuccessful.");
                return;
            }
            else if (db.getBranchByBranchName(branchName).addBranchManager(staffID)){
                manager.setBranchName(branchName);
                System.out.printf("Successfully transferred Branch Manager (%s) to Branch (%s)", staffID, branchName);
            }
            else System.out.println("Transfer unsuccessful.");
        }
        else if (db.getStaff(staffID).getUserType() == UserType.ADMIN){
            System.out.println("Transfer unsuccesful. Unable to transfer Admin.");
        }
    };

    public void editPaymentMethod(){
        ;
    }

    private void addPaymentMethod(String branchName){
        db.addPaymentMethod();
    };

    private void removePaymentMethod(String branchName){
        db.removePaymentMethod();
    };

    public void addBranch(){
        sc = new Scanner(System.in);
        System.out.println("Enter branch name: ");
        String branchName=sc.nextLine();

        System.out.println("Enter branch location: ");
        String branchLocation=sc.nextLine();

        System.out.println("Enter staff quota: ");
        int staffQuota = sc.nextInt();
        sc.nextLine();

        Branch newBranch = new Branch(branchName, branchLocation, staffQuota); 
        db.addBranch(newBranch);
        System.out.println("You have successfully opened a branch.");
 /*        }
        else{
            System.out.println("Unsuccessful in opening of branch.");
        }
        return newBranch; */
    };

    public void removeBranch(String branchName){
        db.removeBranch(branchName);
    };


    // From IStaffManagement
    public void displayStaffList(){
        sc = new Scanner(System.in);
        ArrayList<String> staffIDsList = db.getStaffIDs();
        System.out.println("Choose Filter: ");
        System.out.println("(1) Display all ");
        System.out.println("(2) Filter by Branch ");
        System.out.println("(3) Filter by Role ");
        System.out.println("(4) Filter by Gender ");
        System.out.println("(5) Filter by Age ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1:
                for (String staffID : staffIDsList){
                    Staff staff = db.getStaff(staffID);
                    System.out.println("Name: " + staff.getName());
                    System.out.println("StaffID: " + staff.getStaffID());
                    System.out.println("Role: " + staff.getUserType().stringFromUserType());
                    System.out.println("Gender: " + staff.getGender());
                    System.out.println("Age: " + staff.getAge());
                    System.out.println("Branch: " + staff.getBranchName());
                    System.out.println();
                }
                break;
            case 2: 
                System.out.println("Branch: ");
                String branch = sc.nextLine();
                for (String staffID : staffIDsList){
                    Staff staff = db.getStaff(staffID);
                    if (staff.getBranchName().equals(branch)){
                        System.out.println("Name: " + staff.getName());
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Gender: "+ staff.getGender());
                        System.out.println("Age: " + staff.getAge());   
                    }
                }
                break;
            case 3:
                System.out.println("Role: S/M/A");
                char roleInput = sc.next().charAt(0);
                try {
                    UserType role = UserType.fromCode(roleInput);
                    for (String staffID : staffIDsList){
                        Staff staff = db.getStaff(staffID);
                        if (staff.getUserType() == role){
                            System.out.println("Name: " + staff.getName());
                            System.out.println("StaffID: " + staff.getStaffID());
                            System.out.println("Gender: "+ staff.getGender());
                            System.out.println("Age: " + staff.getAge());
                            System.out.println("Branch: " + staff.getBranchName());
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid role chosen.");
                }
                break;
            case 4: 
                System.out.println("Gender: M/F");
                char gender = sc.next().charAt(0);
                for (String staffID : staffIDsList){
                    Staff staff = db.getStaff(staffID);
                    if (staff.getGender() == gender){
                        System.out.println("Name: " + staff.getName());
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Age: " + staff.getAge());
                        System.out.println("Branch: " + staff.getBranchName());
                    }
                }
                break;
            case 5:
                System.out.println("Age: ");
                int age = sc.nextInt();
                for (String staffID : staffIDsList){
                    Staff staff = db.getStaff(staffID);
                    if (staff.getAge() == age){
                        System.out.println("Name: " + staff.getName());
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Gender: "+ staff.getGender());
                        System.out.println("Branch: " + staff.getBranchName());
                    }
                }
                break;
            default:
                break;
        }
    }
}