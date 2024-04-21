package Accounts;

import java.util.ArrayList;
import java.util.Scanner; 

import Branch.*;
import Database.InMemoryDatabase;


public class Admin extends Employee implements IAdminManagement, IStaffManagement {
    Scanner sc = new Scanner(System.in);

    public Admin(String name, String staffID, char gender, int age){
        super(name, staffID, UserType.ADMIN, gender, age);
    }

    // From IAdminManagement
    public void addStaff(String name, String staffID, char gender, int age, String branchID){
        Staff newStaff = new Staff(name, staffID, gender, age, branchID);
        Account newAccount = new Account(staffID);
        InMemoryDatabase.addStaff(newStaff);
        InMemoryDatabase.addAccount(newAccount);
    };

    public void removeStaff(String staffID){
        InMemoryDatabase.removeStaff(staffID);
    };

    public void editStaff(String staffID){
        System.out.println("What would you like to edit?");
        System.out.println("(1) Name");
        System.out.println("(2) Gender");
        System.out.println("(3) Age");
        Staff staff = InMemoryDatabase.getStaff(staffID);
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

    public void assignManager(String staffID, String branchName){
        if (InMemoryDatabase.getStaff(staffID) == null) {
            System.out.println("Manager does not exist. Manager assignment unsuccessful.");
        }
        else if (InMemoryDatabase.getBranchByBranchName(branchName) == null) {
            System.out.println("Branch does not exist. Manager assignment unsuccessful.");
        }
        else {
            InMemoryDatabase.getBranchByBranchName(branchName).addBranchManager(staffID);
            System.out.printf("Successfully assigned Branch Manager (%s) to Branch (%s).", staffID, branchName);
        }
    };

    public void transferEmployee(String staffID, String branchName){
        if (InMemoryDatabase.getStaff(staffID) == null) {
            System.out.println("Staff/Manager does not exist. Employee assignment unsuccessful.");
        }
        else if (InMemoryDatabase.getBranchByBranchName(branchName) == null) {
            System.out.println("Branch does not exist. Employee assignment unsuccessful.");
        }
        
        if (InMemoryDatabase.getStaff(staffID).getUserType() == UserType.STAFF){
            Staff staff = InMemoryDatabase.getStaff(staffID);
            String oldBranchName = staff.getBranchName();
            // Removes staff from their old branch.
            if (InMemoryDatabase.getBranchByBranchName(oldBranchName).removeStaff(staffID) == false){
                System.out.println("Transfer unsuccessful.");
                return;
            }
            // Transfers staff to new branch.
            else if (InMemoryDatabase.getBranchByBranchName(branchName).addStaff(staffID)){
                staff.setBranchName(branchName);
                System.out.printf("Successfully transferred Staff (%s) to Branch (%s)", staffID, branchName);
            }
            else System.out.println("Transfer unsuccessful.");
        }
        else if (InMemoryDatabase.getStaff(staffID).getUserType() == UserType.BRANCH_MANAGER){
            BranchManager manager = InMemoryDatabase.getBranchManager(staffID);
            String oldBranchName = manager.getBranchName();

            if (InMemoryDatabase.getBranchByBranchName(oldBranchName).removeBranchManager(staffID) ==  false){
                System.out.println("Transfer unsuccessful.");
                return;
            }
            else if (InMemoryDatabase.getBranchByBranchName(branchName).addBranchManager(staffID)){
                manager.setBranchName(branchName);
                System.out.printf("Successfully transferred Branch Manager (%s) to Branch (%s)", staffID, branchName);
            }
            else System.out.println("Transfer unsuccessful.");
        }
        else if (InMemoryDatabase.getStaff(staffID).getUserType() == UserType.ADMIN){
            System.out.println("Transfer unsuccesful. Unable to transfer Admin.");
        }
    };

    public void addPaymentMethod(String branchName){
        InMemoryDatabase.getBranchByBranchName(branchName).addPaymentMethod();
    };

    public void removePaymentMethod(String branchName){
        InMemoryDatabase.getBranchByBranchName(branchName).removePaymentMethod();
    };

    public void addBranch(String branchName, String branchLocation, int staffQuota){
        Branch newBranch = new Branch(branchName, branchLocation, staffQuota);
        InMemoryDatabase.addBranch(newBranch);
    };

    public void removeBranch(String branchName){
        InMemoryDatabase.removeBranch(branchName);
    };


    // From IStaffManagement
    public void displayStaffList(){
        ArrayList<String> staffIDsList = InMemoryDatabase.getStaffIDs();
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
                for (String staffID : staffIDsList){
                    Staff staff = InMemoryDatabase.getStaff(staffID);
                    System.out.println("StaffID: " + staff.getStaffID());
                    System.out.println("Branch: " + staff.getBranchName());
                    System.out.println("Name: " + staff.getName());
                    System.out.println("Role: " + staff.getUserType().stringFromUserType());
                    System.out.println("Age: " + getAge());
                    System.out.println("Gender: " + getGender());
                }
                break;
            case 2: 
                System.out.println("Branch: ");
                String branch = sc.nextLine();
                for (String staffID : staffIDsList){
                    Staff staff = InMemoryDatabase.getStaff(staffID);
                    if (staff.getBranchName() == branch){
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Name: " + staff.getName());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Age: " + getAge());
                        System.out.println("Gender: "+ getGender());
                    }
                }
                break;
            case 3:
                System.out.println("Role: S/M/A");
                char roleInput = sc.next().charAt(0);
                try {
                    UserType role = UserType.fromCode(roleInput);
                    for (String staffID : staffIDsList){
                        Staff staff = InMemoryDatabase.getStaff(staffID);
                        if (staff.getUserType() == role){
                            System.out.println("StaffID: " + staff.getStaffID());
                            System.out.println("Branch: " + staff.getBranchName());
                            System.out.println("Name: " + staff.getName());
                            System.out.println("Age: " + getAge());
                            System.out.println("Gender: "+ getGender());
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
                    Staff staff = InMemoryDatabase.getStaff(staffID);
                    if (staff.getGender() == gender){
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Branch: " + staff.getBranchName());
                        System.out.println("Name: " + staff.getName());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Age: " + getAge());
                    }
                }
                break;
            case 5:
                System.out.println("Age: ");
                int age = sc.nextInt();
                for (String staffID : staffIDsList){
                    Staff staff = InMemoryDatabase.getStaff(staffID);
                    if (staff.getAge() == age){
                        System.out.println("StaffID: " + staff.getStaffID());
                        System.out.println("Branch: " + staff.getBranchName());
                        System.out.println("Name: " + staff.getName());
                        System.out.println("Role: " + staff.getUserType().stringFromUserType());
                        System.out.println("Gender: "+ getGender());
                    }
                }
                break;
            default:
                break;
        }
    }
}

