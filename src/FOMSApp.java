import java.lang.System;

import Accounts.*;
import Branch.*;
import Customer.*;
import Display.*;
import DataPersistence.*;
import java.util.Scanner;

public class FOMSApp{
    // Declaring ANSI_RESET so that we can reset the color 
    public static final String ANSI_RESET = "\u001B[0m"; 
    // Declaring the color theme
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //NEED TO CALL DATA PERSISTENCE HERE


        Branches branches = new Branches();
    
        // I THINK WE MAY NEED TO MAKE THE DISPLAY A CLASS ALSO
        System.out.println(ANSI_CYAN + "Welcome to the Fastfood Ordering Management System." + ANSI_RESET);
        System.out.println("Are you a customer? (Y/N)");
        boolean isCustomer = (sc.next().charAt(0) == 'Y');
        if (isCustomer){
            System.out.println("isCustomer == TRUE");
        }
        else {
            System.out.println("Enter your branchID: ");
            int branchID = sc.nextInt();
            System.out.println("Enter your staffID: ");
            String staffID = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            if (branches.getSpecificBranch(branchID).findStaff(staffID).validateLogin(staffID, password)){
                System.out.println("Login successful.");
            }
            else System.out.println("Login unsuccessful.");
        }

        sc.close();
        return;
    }
}