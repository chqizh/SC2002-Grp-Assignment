package Database;

import Accounts.*;
import Branch.*;
import Customer.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<Staff> staffTable;
    private List<MenuItem> menuItemTable;
    private List<Branch> branchTable;
    private List<Order> orderTable;

    public InMemoryDatabase() {
        this.staffTable = new ArrayList<>();
        this.menuItemTable = new ArrayList<>();
        this.branchTable = new ArrayList<>();
        this.orderTable = new ArrayList<>();

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

    // Methods to retrieve and update data
    // ...

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