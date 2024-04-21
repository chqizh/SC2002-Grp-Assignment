package Branch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;
import Customer.Customer;

public class Branch implements Serializable {
    private String branchName;
    private String branchLocation;
    private ArrayList <String> branchManagerIDs = new ArrayList <String> ();
    private int numManagers = 1;
    private ArrayList <String> staffIDs = new ArrayList <String> ();
    private int currentNumStaff;
    private int staffQuota;
    private MenuItems branchMenu = new MenuItems();
    private OrderList branchOrders = new OrderList();
    private ArrayList <String> paymentMethods = new ArrayList<String> ();
    
    public Branch (String branchName, String branchLocation, int staffQuota){
       // this.branchID = nextBranchID;
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
        //nextBranchID++;
        this.paymentMethods.addAll(Customer.getPaymentMethods());
    }
    
    public String getBranchName() {
        return branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public ArrayList<String> getBranchManagers() {
        return branchManagerIDs;
    }

    public boolean addBranchManager(String staffID){
        if (branchManagerIDs.contains(staffID)){
            System.out.printf("Branch already contains Branch Manager with staffID %s.", staffID);
            return false;            
        }
        else if (branchManagerIDs.size() >= numManagers){
            System.out.printf("Branch already maximum number of Branch Managers.", staffID);
            return false;
        }
        else {
            branchManagerIDs.add(staffID);
            return true;
        }
    }

    public boolean removeBranchManager(String staffID){
        if (branchManagerIDs.contains(staffID)){
            System.out.printf("Branch Manager with staffID %s successfully removed.", staffID);
            branchManagerIDs.remove(staffID);
            System.out.printf("Number of Branch Managers is below required amount. Please add another Branch Manager.", staffID);
            Scanner sc = new Scanner(System.in);
            String newBranchManager = sc.next();
            addBranchManager(newBranchManager);
            return true;            
        }
        else {
            return false;
        }
    }

    public void setnumManagers(int currentNumStaff){
        if (currentNumStaff>=1 && currentNumStaff<=4){
            this.numManagers=1;
        }

        else if (currentNumStaff>=5 && currentNumStaff<=8){
            this.numManagers=2;
        }

        else if (currentNumStaff>=9 && currentNumStaff<=15){
            this.numManagers=4;
        }
    }

    public int getnumManagers(){
        return numManagers;
    }

    public ArrayList<String> getStaffIDs() {
        return staffIDs;
    }

    public boolean addStaff(String staffID){
        if (staffIDs.contains(staffID)){
            System.out.printf("Branch already contains Staff with staffID %s", staffID);
            return false;
        }
        else if (staffIDs.size() >= staffQuota){
            System.out.println("Staff quota is already filled.");
            return false;
        }
        else {
            staffIDs.add(staffID);
            return true;
        }
    }

    public boolean removeStaff(String staffID){
        if (!staffIDs.contains(staffID)){
            System.out.printf("Branch does not contain Staff with staffID %s", staffID);
            return false;
        }
        else {
            staffIDs.remove(staffID);
            return true;
        }
    }

    public int getCurNumStaff(){
        return currentNumStaff;
    }

    public int getStaffQuota(){
        return staffQuota;
    }

    public MenuItems getBranchMenu() {
        return branchMenu;
    }

    public OrderList getBranchOrders() {
        return branchOrders;
    }

    public boolean addPaymentMethod(){
        System.out.println("Which payment method would you like to add?");
        System.out.println("(1) Bank Card");
        System.out.println("(2) Paynow");
        System.out.println("(3) Paypal");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        
        switch (choice){
            case 1:
                if (!paymentMethods.contains("Bank Card")){ 
                    paymentMethods.add("Bank Card");
                    System.out.println("Payment method successfully added to branch.");
                }
                else System.out.println("Payment method already accepted in branch.");
                break;
            case 2:
                if (!paymentMethods.contains("Paynow")){ 
                    paymentMethods.add("Paynow");
                    System.out.println("Payment method successfully added to branch.");
                }
                else System.out.println("Payment method already accepted in branch.");
                break;
            case 3:
                if (!paymentMethods.contains("Paypal")){ 
                    paymentMethods.add("Paypal");
                    System.out.println("Payment method successfully added to branch.");
                }
                else System.out.println("Payment method already accepted in branch.");
                break;
            default:
                System.out.println("Invalid option chosen.");
                return false;
        }

        return true;
    }

    public boolean removePaymentMethod(){
        System.out.println("Which payment method would you like to remove?");
        System.out.println("(1) Bank Card");
        System.out.println("(2) Paynow");
        System.out.println("(3) Paypal");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        
        switch (choice){
            case 1:
                if (paymentMethods.contains("Bank Card")){ 
                    paymentMethods.remove("Bank Card");
                    System.out.println("Payment method successfully removed to branch.");
                }
                else System.out.println("Payment method already not accepted in branch.");
                break;
            case 2:
                if (paymentMethods.contains("Paynow")){ 
                    paymentMethods.remove("Paynow");
                    System.out.println("Payment method successfully removed to branch.");
                }
                else System.out.println("Payment method already not accepted in branch.");
                break;
            case 3:
                if (paymentMethods.contains("Paypal")){ 
                    paymentMethods.remove("Paypal");
                    System.out.println("Payment method successfully removed to branch.");
                }
                else System.out.println("Payment method already not accepted in branch.");
                break;
            default:
                System.out.println("Invalid option chosen.");
                return false;
        }
        
        return true;
    }
}
