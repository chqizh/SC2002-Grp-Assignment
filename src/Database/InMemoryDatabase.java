package Database;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;


import Accounts.*;
import Branch.*;

public class InMemoryDatabase implements Serializable{
    private static Map<String, Account> accounts;
    private static Map<String, Branch> branches;
    private static Map<String, Staff> staffMap;
    private static Map<String, BranchManager> branchManagerMap;
    private static Map<String, Admin> adminMap;

    public InMemoryDatabase() {
        accounts = new HashMap<>();
        branches = new HashMap<>();
        staffMap = new HashMap<>();
        branchManagerMap = new HashMap<>();
        adminMap = new HashMap<>();
    }

    public static void addAccount(Account account) {
        accounts.put(account.getStaffID(), account);
    }

    public static void removeAccount(String accountID) {
        accounts.remove(accountID);
    }

    public static void addBranch(Branch branch) {
        branches.put(branch.getBranchName(), branch);
    }

    public static void removeBranch(String branchName) {
        branches.remove(branchName);
    }

    public static void addStaff(Staff staff) {
        staffMap.put(staff.getStaffID(), staff);
    }

    public static void removeStaff(String staffID){
        staffMap.remove(staffID);
    }

    public static void addBranchManager(BranchManager branchManager) {
        branchManagerMap.put(branchManager.getStaffID(), branchManager);
    }

    public static void addAdmin(Admin admin) {
        adminMap.put(admin.getStaffID(), admin);
    }

    public static Account getAccountByStaffID(String staffID) {
        return accounts.get(staffID);
    }

    public static Branch getBranchByBranchName(String branchName) {
        return branches.get(branchName);
    }

    public static Staff getStaff(String staffID) {
        return staffMap.get(staffID);
    }

    public static ArrayList<String> getStaffIDs(){
        ArrayList<String> staffIDsList = new ArrayList<String>(staffMap.keySet());
        return staffIDsList;
    }

    public static BranchManager getBranchManager(String staffID) {
        return branchManagerMap.get(staffID);
    }

    public static Admin getAdmin(String staffID) {
        return adminMap.get(staffID);
    }

    // To validate account
    public static Employee validateEmployee(String staffID, String password) {
        Account account = getAccountByStaffID(staffID);
        if (account == null){                     
            System.out.println("StaffID not found!");
            return null;
            }

        else if (account.validateLogin(password)) {
            if (staffMap.containsKey(staffID)) {
                return staffMap.get(staffID);
            } 
            else if (branchManagerMap.containsKey(staffID)) {
                return branchManagerMap.get(staffID);
            } 
            else if (adminMap.containsKey(staffID)) {
                return adminMap.get(staffID);
            }
        }
        return null; // Login failed or staffID not found in any map
    }

}