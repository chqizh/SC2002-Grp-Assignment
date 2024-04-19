package Database;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

import Accounts.*;
import Branch.*;

public class InMemoryDatabase implements Serializable{
    private Map<String, Account> accounts;
    private Map<String, Branch> branches;
    private Map<String, Staff> staffMap;
    private Map<String, BranchManager> branchManagerMap;
    private Map<String, Admin> adminMap;

    public InMemoryDatabase() {
        this.accounts = new HashMap<>();
        this.branches = new HashMap<>();
        this.staffMap = new HashMap<>();
        this.branchManagerMap = new HashMap<>();
        this.adminMap = new HashMap<>();
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getStaffID(), account);
    }

    public void addBranch(Branch branch) {
        this.branches.put(branch.getBranchName(), branch);
    }

    public void addStaff(Staff staff) {
        this.staffMap.put(staff.getStaffID(), staff);
    }

    public void addBranchManager(BranchManager branchManager) {
        this.branchManagerMap.put(branchManager.getStaffID(), branchManager);
    }

    public void addAdmin(Admin admin) {
        this.adminMap.put(admin.getStaffID(), admin);
    }

    public Account getAccountByStaffID(String staffID) {
        return this.accounts.get(staffID);
    }

    public Branch getBranchByBranchName(String branchName) {
        return this.branches.get(branchName);
    }

    public Staff getStaff(String staffID) {
        return this.staffMap.get(staffID);
    }

    public BranchManager getBranchManager(String staffID) {
        return this.branchManagerMap.get(staffID);
    }

    public Admin getAdmin(String staffID) {
        return this.adminMap.get(staffID);
    }

    // To validate account
    public Employee validateEmployee(String staffID, String password) {
        Account account = getAccountByStaffID(staffID);

        if (account != null && account.validateLogin(staffID,password)) {
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