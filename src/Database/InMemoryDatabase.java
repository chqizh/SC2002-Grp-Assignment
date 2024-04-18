package Database;

import Accounts.*;
import Branch.*;
import Customer.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<Account> accountTable;
    private List<Staff> staffTable;
    private List<MenuItem> menuItemTable;
    private List<Branch> branchTable;
    private List<Order> orderTable;

    public InMemoryDatabase() {
        this.accountTable = new ArrayList<>();
        this.staffTable = new ArrayList<>();
        this.menuItemTable = new ArrayList<>();
        this.branchTable = new ArrayList<>();
        this.orderTable = new ArrayList<>();

    }

    public void addAccount(Account account) {
        this.accountTable.add(account);
    }

    public void addStaff(Staff staff) {
        this.staffTable.add(staff);
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItemTable.add(menuItem);
    }

    public void addBranch(Branch branch) {
        this.branchTable.add(branch);
    }

    public void addOrder(Order order) {
        this.orderTable.add(order);
    }

    // To validate account
    public Account validateLogin(String staffID, String password) {
        for (Account account : accountTable) {
            if (account.validateLogin(staffID, password)) {
                return account; // Login successful
            }
        }
        return null; // Login failed
    }

    // Getters for the tables
    public List<Staff> getStaffTable() {
        return staffTable;
    }

    public List<MenuItem> getMenuItemTable() {
        return menuItemTable;
    }

    public List<Branch> getBranchTable() {
        return branchTable;
    }

    public List<Order> getOrderTable() {
        return orderTable;
    }
}