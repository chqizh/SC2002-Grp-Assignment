package Accounts;
import Branch.*;
import Database.InMemoryDatabase;

import java.util.Scanner; 

public class Admin extends Employee implements IAdminManagement, IStaffManagement {

    Scanner sc = new Scanner(System.in);

    // From IAdminManagement
    public void addStaff(String name, String staffID, char gender, int age, String branchID, InMemoryDatabase db){
        Staff newStaff = new Staff(name, staffID, gender, age, branchID);
        Account newAccount = new Account(staffID);
        db.addStaff(newStaff);
        db.addAccount(newAccount);
    };

    public void removeStaff(String staffID, InMemoryDatabase db){
        db.removeStaff(staffID);
    };

    public void editStaff(String staffID, InMemoryDatabase db){
        System.out.println("What would you like to edit?");
        System.out.println("(1) Name");
        System.out.println("(2) Gender");
        System.out.println("(3) Age");
        Staff staff = db.getStaff(staffID);
        try {
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Please enter a new name.");
                    String name = sc.next();
                    staff.setName(name);
                    break;
                case 2:
                    System.out.println("Please enter a new gender.");
                    char gender = sc.next().charAt(0);
                    staff.setGender(gender);
                    break;
                case 3:
                    System.out.println("Please enter a new age.");
                    int age = sc.nextInt();
                    staff.setAge(age);
                    break;
                default:
                    System.out.println("Invalid integer.");
                    break;
            }
        }
        catch (Exception e){
            System.out.println("Invalid integer.");
        }
    };

    public void assignManager(String staffID, String branchName, InMemoryDatabase db){
        if (db.getStaff(staffID) == null) {
            System.out.println("Manager does not exist. Manager assignment unsuccessful.");
        }
        else if (db.getBranchByBranchName(branchName) == null) {
            System.out.println("Manager does not exist. Manager assignment unsuccessful.");
        }
        else {
            db.getBranchByBranchName(branchName).addBranchManager(staffID);
            System.out.printf("Successfully assigned Branch Manager (%s) to Branch (%s).", staffID, branchName);
        }
    };

    public void transfer(InMemoryDatabase db){};

    public void addPayment(InMemoryDatabase db){};

    public void removePayment(InMemoryDatabase db){};

    public void addBranch(String branchName, String branchLocation, int staffQuota, InMemoryDatabase db){
        Branch newBranch = new Branch(branchName, branchLocation, staffQuota);
        db.addBranch(newBranch);
    };

    public void removeBranch(String branchName, InMemoryDatabase db){
        db.removeBranch(branchName);
    };


    // From IStaffManagement
    public void displayStaffListAdmin (Staff staffList[]){

        int choice;
        System.out.println("Choose Filter: ");
        System.out.println("(1) Display all ");
        System.out.println("(2) Filter by Branch ");
        System.out.println("(3) Filter by Role ");
        System.out.println("(4) Filter by Gender ");
        System.out.println("(5) Filter by Age ");
        choice=sc.nextInt();

        switch(choice){
            case 1:
                for (Staff staff : staffList){
                    System.out.println("Staff "+ i++);
                    System.out.println("Name: " + staff.getName());
                    System.out.println("Role: " + staff.getRole());
                    System.out.println("Age: "+getAge());
                    System.out.println("Gender: "+getGender());
                }
                break;
            case 2: 
                System.out.println("Branch: ");
                String branch = sc.nextLine();
                for (Staff staff : staffList){
                    if (staff.getBranchID() == branch){
                        System.out.println("Staff "+ i++);
                        System.out.println("Name: " + staff.getName());
                        System.out.println("Role: " + staff.getUserType());
                        System.out.println("Age: "+getAge());
                        System.out.println("Gender: "+getGender());
                    }
                }
                break;
            case 3:
                System.out.println("Role: ");
                String role = sc.nextLine();
                 break;
            case 4: 
                System.out.println("Gender: ");
                String gender= sc.nextLine();
                break;
            case 5:
                System.out.println("Age: ");
                String age= sc.nextLine();
                break;
            default:
                break;
        }
    }
}

