package Database;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.Serializable;

import Accounts.*;
import Branch.*;
import Customer.*;

public class InMemoryDatabase implements Serializable {
    private Map<String, Account> accounts;
    private Map<String, Branch> branches;
    private Map<String, Staff> staffMap;
    private Map<String, BranchManager> branchManagerMap;
    public Map<String, Admin> adminMap;
    public ArrayList<Payment> paymentMethods;

    public InMemoryDatabase() {
        this.accounts = new HashMap<>();
        this.branches = new HashMap<>();
        this.staffMap = new HashMap<>();
        this.branchManagerMap = new HashMap<>();
        this.adminMap = new HashMap<>();
        this.paymentMethods = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getStaffID(), account);
    }

    public void removeAccount(String accountID) {
        this.accounts.remove(accountID);
    }

    public void addBranch(Branch branch) {
        this.branches.put(branch.getBranchName(), branch);
    }

    public void removeBranch(String branchName) {
        this.branches.remove(branchName);
    }

    public void addStaff(Staff staff) {
        this.staffMap.put(staff.getStaffID(), staff);
    }

    public void removeStaff(String staffID) {
        this.staffMap.remove(staffID);
    }

    public void addBranchManager(BranchManager branchManager) {
        this.branchManagerMap.put(branchManager.getStaffID(), branchManager);

    }
    public void removeBranchManager(String staffID) {
        this.branchManagerMap.remove(staffID);
    }

    public void addAdmin(Admin admin) {
        this.adminMap.put(admin.getStaffID(), admin);
    }

    public void removeAdmin(String staffID) {
        this.adminMap.remove(staffID);
    }

    public void addPaymentMethod(Payment paymentMethod) {
        this.paymentMethods.add(paymentMethod);
    }

    public void removePaymentMethod(Payment paymentMethod) {
        this.paymentMethods.remove(paymentMethod);
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

    public ArrayList<String> getStaffIDs(){
        ArrayList<String> staffIDsList = new ArrayList<String>(staffMap.keySet());
        return staffIDsList;
    }

    public BranchManager getBranchManager(String staffID) {
        return this.branchManagerMap.get(staffID);
    }

    public Admin getAdmin(String staffID) {
        return this.adminMap.get(staffID);
    }

    public ArrayList<Payment> getPaymentMethods() {
        return new ArrayList<>(this.paymentMethods);
    }
    public Employee getEmployee(String staffID) {
        Account account = getAccountByStaffID(staffID);
        if (account != null) {
            if (this.staffMap.containsKey(staffID)) {
                return this.staffMap.get(staffID);
            } else if (this.branchManagerMap.containsKey(staffID)) {
                return this.branchManagerMap.get(staffID);
            } else if (this.adminMap.containsKey(staffID)) {
                return this.adminMap.get(staffID);
            }
        }
        return null;
    }
}