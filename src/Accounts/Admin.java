package Accounts;
import Branch.*;
import java.util.Scanner; 

public class Admin extends Employee implements IAdminManagement, IStaffManagement {

    Scanner sc = new Scanner(System.in);

    public void addStaff(String name, char gender, int age, String branchID){
        Staff newStaff = new Staff(name, gender, age, branchID);
        newStaff.getBranchID();
    }

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

