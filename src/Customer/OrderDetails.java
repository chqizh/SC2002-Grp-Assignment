package Customer;

import java.util.List;

import OldMenu.MenuItem;

public class OrderDetails {
    private int orderId;
    private int branchId;
    private List<MenuItem> items;

    public OrderDetails(int orderId, int branchId, List<MenuItem> items) {
        this.orderId = orderId;
        this.branchId = branchId;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBranchId() {
        return branchId;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}