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


/**
 * Represents an in-memory database storing accounts, branches, and employees.
 */
public class InMemoryDatabase implements Serializable {
    private Map<String, Account> accounts;
    private Map<String, Branch> branches;
    private Map<String, Staff> staffMap;
    private Map<String, BranchManager> branchManagerMap;
    private Map<String, Admin> adminMap;
    private Map<String, Boolean> paymentMethods;
    private boolean branchesInitialized;
    private boolean staffInitialized;
    private boolean menuInitialized;

    /**
     * Constructs an InMemoryDatabase with default values.
     */
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
        branchesInitialized = false;
        staffInitialized = false;
        menuInitialized = false;
    }

    /**
     * Checks if branches are initialized.
     * @return true if branches are initialized, false otherwise
     */
    public boolean getBranchesInitialized() {
        return this.branchesInitialized;
    } 

    /**
     * Sets the initialization status of branches.
     * @param bool the status to set
     */
    public void setBranchesInitialized(Boolean bool) {
        this.branchesInitialized = bool;
    } 

    /**
     * Checks if staff is initialized.
     * @return true if staff is initialized, false otherwise
     */
    public boolean getStaffInitialized() {
        return this.staffInitialized;
    } 

    /**
     * Sets the initialization status of staff.
     * @param bool the status to set
     */
    public void setStaffInitialized(Boolean bool) {
        this.staffInitialized = bool;
    } 

    /**
     * Checks if the menu is initialized.
     * @return true if the menu is initialized, false otherwise
     */
    public boolean getMenuInitialized() {
        return this.menuInitialized;
    } 

    /**
     * Sets the initialization status of the menu.
     * @param bool the status to set
     */
    public void setMenuInitialized(Boolean bool) {
        this.menuInitialized = bool;
    } 
    
    /**
     * Retrieves the map of accounts.
     * @return the accounts map
     */
    public Map<String, Account> getAccountsMap() {
        return accounts;
    }

    /**
     * Retrieves the map of branches.
     * @return the branches map
     */
    public Map<String, Branch> getBranchesMap() {
        return branches;
    }

    /**
     * Retrieves the map of staff.
     * @return the staff map
     */
    public Map<String, Staff> getStaffMap() {
        return staffMap;
    }

    /**
     * Retrieves the map of branch managers.
     * @return the branch managers map
     */
    public Map<String, BranchManager> getBranchManagerMap() {
        return branchManagerMap;
    }

    /**
     * Retrieves the map of admins.
     * @return the admins map
     */
    public Map<String, Admin> getAdminMap() {
        return adminMap;
    }

    /**
     * Adds an account to the database.
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        this.accounts.put(account.getStaffID(), account);
    }

    /**
     * Removes an account from the database.
     * @param accountID The ID of the account to be removed.
     */
    public void removeAccount(String accountID) {
        this.accounts.remove(accountID);
    }

    /**
     * Retrieves a list of all branch names in the database.
     * @return The list of all branch names.
     */
    public ArrayList<String> getAllBranchNames(){
        ArrayList<String> allBranchNamesList= new ArrayList<>(this.branches.keySet());
        return allBranchNamesList;
    }

    /**
     * Adds a branch to the database.
     * Branches must not contain an existing branch with the same branchName as the new branch.
     * @param branch The branch to be added.
     * @return True if the branch is added successfully, false otherwise.
     */
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

    /**
     * Removes a branch from the database.
     * @param branchName The name of the branch to be removed.
     * @return True if the branch is removed successfully, false otherwise.
     */
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

    /**
     * Adds a staff member to the database.
     * @param staff The staff member to be added.
     * @return True if the staff member is added successfully, false otherwise.
     */
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

    /**
     * Removes a staff member from the database.
     * @param staffID The ID of the staff member to be removed.
     */
    public void removeStaff(String staffID) {
        Staff staff = this.getStaff(staffID);
        String branchName = staff.getBranchName();
        this.getBranchByBranchName(branchName).removeStaff(staffID);
        this.staffMap.remove(staffID);
    }
    
    /**
     * Adds a branch manager to the database.
     * @param branchManager The branch manager to be added.
     * @return True if the branch manager is added successfully, false otherwise.
     */
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

    /**
     * Removes a branch manager from the database.
     * @param staffID The ID of the branch manager to be removed.
     * @return True if the branch manager is removed successfully, false otherwise.
     */
    public boolean removeBranchManager(String staffID) {
        BranchManager manager = this.getBranchManager(staffID);
        String branchName = manager.getBranchName();
        if (this.getBranchByBranchName(branchName).removeBranchManager(staffID)){
            this.branchManagerMap.remove(staffID);
            return true;
        }
        else return false;
    }

    /**
     * Adds an admin to the database.
     * @param admin The admin to be added.
     * @return True if the admin is added successfully, false otherwise.
     */
    public boolean addAdmin(Admin admin) {
        if (this.adminMap.containsKey(admin.getStaffID()) == false){
            this.adminMap.put(admin.getStaffID(), admin);
            return true;
        }
        else return false;
    }

    /**
     * Removes an admin from the database.
     * @param staffID The ID of the admin to be removed.
     */
    public void removeAdmin(String staffID) {
        this.adminMap.remove(staffID);
    }

    /**
     * Retrieves a list of all payment methods in the database.
     * @return The list of all payment methods.
     */
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

    /**
     * Retrieves the status of a payment method.
     * @param paymentMethod The payment method to retrieve the status for.
     * @return True if the payment method is enabled, false otherwise.
     */
    public boolean getPaymentMethodsStatus(String paymentMethod) {
        return this.paymentMethods.get(paymentMethod);
    }

    /**
     * Toggles the status of a payment method.
     * @param paymentMethod The payment method to toggle.
     * @return True if the payment method status is toggled successfully, false otherwise.
     */
    public boolean togglePaymentMethod(String paymentMethod) {
        if (this.paymentMethods.containsKey(paymentMethod)){
            if (this.paymentMethods.get(paymentMethod) == true) this.paymentMethods.replace(paymentMethod, false);
            else this.paymentMethods.replace(paymentMethod, true);
            return true;
        }
        else return false;
    }

    /**
     * Retrieves the account associated with the given staff ID.
     * @param staffID The staff ID of the account to retrieve.
     * @return The account associated with the given staff ID, or null if not found.
     */
    public Account getAccountByStaffID(String staffID) {
        return this.accounts.get(staffID);
    }

    /**
     * Retrieves the branch associated with the given branch name.
     * @param branchName The name of the branch to retrieve.
     * @return The branch associated with the given branch name, or null if not found.
     */
    public Branch getBranchByBranchName(String branchName) {
        return this.branches.get(branchName);
    }

    /**
     * Retrieves the staff member associated with the given staff ID.
     * @param staffID The staff ID of the staff member to retrieve.
     * @return The staff member associated with the given staff ID, or null if not found.
     */
    public Staff getStaff(String staffID) {
        return this.staffMap.get(staffID);
    }
 
    /**
     * Retrieves a list of all employee IDs in the database.
     * @return The list of all employee IDs.
     */
    public ArrayList<String> getAllEmployeeIDs(){
        ArrayList<String> employeeIDsList = new ArrayList<String>(this.staffMap.keySet());
        employeeIDsList.addAll(this.branchManagerMap.keySet());
        employeeIDsList.addAll(this.adminMap.keySet());
        return employeeIDsList;
    }

    /**
     * Retrieves the branch manager associated with the given staff ID.
     * @param staffID The staff ID of the branch manager to retrieve.
     * @return The branch manager associated with the given staff ID, or null if not found.
     */
    public BranchManager getBranchManager(String staffID) {
        return this.branchManagerMap.get(staffID);
    }

    /**
     * Retrieves the admin associated with the given staff ID.
     * @param staffID The staff ID of the admin to retrieve.
     * @return The admin associated with the given staff ID, or null if not found.
     */
    public Admin getAdmin(String staffID) {
        return this.adminMap.get(staffID);
    }

    /**
     * Retrieves the employee associated with the given staff ID.
     * @param staffID The staff ID of the employee to retrieve.
     * @return The employee associated with the given staff ID, or null if not found.
     */
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

    /**
     * Adds a menu item to the specified branch's menu.
     * @param branchName The name of the branch.
     * @param menuItem The menu item to add.
     * @return True if the menu item is added successfully, false otherwise.
     */
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