package Database;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

import Accounts.*;
import Branch.*;
import Menu.MenuItem;

public class InMemoryDatabase implements Serializable {
    private Map<String, Account> accounts;
    private Map<String, Branch> branches;
    private Map<String, Staff> staffMap;
    private Map<String, BranchManager> branchManagerMap;
    private Map<String, Admin> adminMap;
    private Map<String, Boolean> paymentMethods;

    public InMemoryDatabase() {
        this.accounts = new HashMap<>();
        this.branches = new HashMap<>();
        this.staffMap = new HashMap<>();
        this.branchManagerMap = new HashMap<>();
        this.adminMap = new HashMap<>();
        this.paymentMethods = new HashMap<>();
        this.paymentMethods.put("Bank Card", true);
        this.paymentMethods.put("PayPal", true);
        this.paymentMethods.put("PayNow", true);
    }

    public Map<String, Account> getAccountsMap() {
        return accounts;
    }

    public Map<String, Branch> getBranchesMap() {
        return branches;
    }

    public Map<String, Staff> getStaffMap() {
        return staffMap;
    }

    public Map<String, BranchManager> getBranchManagerMap() {
        return branchManagerMap;
    }

    public Map<String, Admin> getAdminMap() {
        return adminMap;
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getStaffID(), account);
    }

    public void removeAccount(String accountID) {
        this.accounts.remove(accountID);
    }

    public ArrayList<String> getAllBranchNames(){
        ArrayList<String> allBranchNamesList= new ArrayList<>(this.branches.keySet());
        return allBranchNamesList;
    }

    public boolean addBranch(Branch branch) {
        // Branches must not contain existing branch with same branchName as new branch.
        if (branches.get(branch.getBranchName()) == null){
            this.branches.put(branch.getBranchName(), branch);
            return true;
        }
        else {
            System.out.printf("Branch with %s as branchName already exists.\n", branch.getBranchName());
            return false;
        }
    }

    public boolean removeBranch(String branchName) {
        if (this.branches.containsKey(branchName)) {
            Branch branch = this.branches.get(branchName);
            ArrayList<String> staffIDs = branch.getStaffIDs();
            ArrayList<String> branchManagerIDs = branch.getBranchManagerIDs();
            for (String staffID : staffIDs) this.staffMap.remove(staffID);
            for (String branchManagerID : branchManagerIDs) this.branchManagerMap.remove(branchManagerID);
            this.branches.remove(branchName);
            return true;
        }
        else {
            System.out.printf("%s branch does not exist.\n", branchName);
            return false;
        }
    }

    public boolean addStaff(Staff staff) {
        String branchName = staff.getBranchName();
        String staffID = staff.getStaffID();
        Branch branch = getBranchByBranchName(branchName);

        // Does not add staff if branch does not exist yet
        if (branch == null){
            return false;
        }
        else {
            if (branch.addStaff(staffID)){
                this.staffMap.put(staffID, staff);
                return true;
            }
            else return false;
        }
    }

    public void removeStaff(String staffID) {
        Staff staff = this.getStaff(staffID);
        String branchName = staff.getBranchName();
        this.getBranchByBranchName(branchName).removeStaff(staffID);
        this.staffMap.remove(staffID);
    }

    public boolean addBranchManager(BranchManager branchManager) {
        String branchName = branchManager.getBranchName();
        String staffID = branchManager.getStaffID();
        Branch branch = getBranchByBranchName(branchName);

        if (branch == null) return false;
        else {
            if (branch.addBranchManager(staffID)){
                this.branchManagerMap.put(staffID, branchManager);
                return true;
            }
            else return false;
        }
    }

    public boolean removeBranchManager(String staffID) {
        BranchManager manager = this.getBranchManager(staffID);
        String branchName = manager.getBranchName();
        if (this.getBranchByBranchName(branchName).removeBranchManager(staffID)){
            this.branchManagerMap.remove(staffID);
            return true;
        }
        else return false;
    }

    public boolean addAdmin(Admin admin) {
        if (this.adminMap.containsKey(admin.getStaffID()) == false){
            this.adminMap.put(admin.getStaffID(), admin);
            return true;
        }
        else return false;
    }

    public void removeAdmin(String staffID) {
        this.adminMap.remove(staffID);
    }

    public ArrayList<String> getPaymentMethods() {
        ArrayList<String> paymentMethodNames = new ArrayList<>(this.paymentMethods.keySet());
        Collections.sort(paymentMethodNames, new Comparator<String>() {
            @Override
            public int compare(String p1, String p2) {
                return p1.compareTo(p2);
            }
        });

        // Java 8 only
        // paymentMethodNames.sort(Comparator.comparing(Payment::getPaymentMethodName));

        return paymentMethodNames;
    }

    public boolean getPaymentMethodsStatus(String paymentMethod) {
        return this.paymentMethods.get(paymentMethod);
    }

    public boolean togglePaymentMethod(String paymentMethod) {
        if (this.paymentMethods.containsKey(paymentMethod)){
            if (this.paymentMethods.get(paymentMethod) == true) this.paymentMethods.replace(paymentMethod, false);
            else this.paymentMethods.replace(paymentMethod, true);
            return true;
        }
        else return false;
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

    public ArrayList<String> getAllEmployeeIDs(){
        ArrayList<String> employeeIDsList = new ArrayList<String>(this.staffMap.keySet());
        employeeIDsList.addAll(this.branchManagerMap.keySet());
        employeeIDsList.addAll(this.adminMap.keySet());
        return employeeIDsList;
    }

    public BranchManager getBranchManager(String staffID) {
        return this.branchManagerMap.get(staffID);
    }

    public Admin getAdmin(String staffID) {
        return this.adminMap.get(staffID);
    }

    public Employee getEmployee(String staffID) {
        Account account = getAccountByStaffID(staffID);
        if (account != null) {
            if (this.staffMap.containsKey(staffID)) {
                return this.staffMap.get(staffID);
            } 
            else if (this.branchManagerMap.containsKey(staffID)) {
                return this.branchManagerMap.get(staffID);
            } 
            else if (this.adminMap.containsKey(staffID)) {
                return this.adminMap.get(staffID);
            }
        }
        return null;
    }

    public boolean addMenuItem(String branchName, MenuItem menuItem){
        Branch branch = this.branches.get(branchName); 
        if (branch != null){
            return branch.getBranchMenu().addItems(menuItem);
        }
        else{
            System.out.printf("Branch %s does not exist.\n", branchName);
            return false;
        }
    }
}