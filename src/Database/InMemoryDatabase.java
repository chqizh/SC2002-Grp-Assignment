package Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.Serializable;

import Accounts.*;
import Branch.*;
import Customer.*;
import Menu.MenuItem;
import Menu.MenuItems;

public class InMemoryDatabase implements Serializable {
    private Map<String, Account> accounts;
    private Map<String, Branch> branches;
    private Map<String, Staff> staffMap;
    private Map<String, BranchManager> branchManagerMap;
    public Map<String, Admin> adminMap;
    public Map<Payment, Boolean> paymentMethods;

    public InMemoryDatabase() {
        this.accounts = new HashMap<>();
        this.branches = new HashMap<>();
        this.staffMap = new HashMap<>();
        this.branchManagerMap = new HashMap<>();
        this.adminMap = new HashMap<>();
        this.paymentMethods = new HashMap<>();
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
        if (this.branches.remove(branchName) != null) return true;
        else {
            System.out.printf("%s branch does not exist.\n", branchName);
            return false;
        }
    }

    public void addStaff(Staff staff) {
        String branchName = staff.getBranchName();
        String staffID = staff.getStaffID();
        Branch branch = getBranchByBranchName(branchName);
        // Create branch if referenced branch does not exist yet.
        if (branch == null){
            branch = new Branch(branchName, branchName, 15);
            this.addBranch(branch);
        }
        branch.addStaff(staff.getStaffID());
        this.staffMap.put(staffID, staff);
    }

    public void removeStaff(String staffID) {
        Staff staff = this.getStaff(staffID);
        String branchName = staff.getBranchName();
        this.getBranchByBranchName(branchName).removeStaff(staffID);
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

    public Set<Payment> getPaymentMethods() {
        Set<Payment> paymentMethodNames = this.paymentMethods.keySet();
        return paymentMethodNames;
    }

    public List<String> getPaymentMethodsNames() {
        List<String> paymentMethodNames = this.paymentMethods.keySet().stream().map(Payment::getPaymentMethodName).collect(Collectors.toList());
        return paymentMethodNames;
    }

    public boolean getPaymentMethodsStatus(Payment paymentMethod) {
        return this.paymentMethods.get(paymentMethod);
    }

    public boolean togglePaymentMethod(Payment paymentMethod) {
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

    public void addMenuItem(String branchName, MenuItem menuitem){
        Branch branch = getBranchByBranchName(branchName);
        ArrayList<MenuItem> menu_items = branch.getBranchMenu().getMenuItemsList();
        menu_items.add(menuitem);   
    }
}