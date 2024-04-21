package Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Branch.OrderList;
import Database.InMemoryDatabase;
import Menu.MenuItem;
import Branch.Branch;

public class Order {
    public enum orderStatusFlags {
        NEW,
        PROCESSED,
        PICKUP,
        COMPLETED
    }

    private int orderID;
    private orderStatusFlags orderStatus;
    private String branchName;
    private ArrayList<MenuItem> orderItems;
    //private int numItems;
    private static int nextOrderID = 1;
    private OrderList orderList;
    private Branch branch;
    private String customisation;

    public Order(Branch branch){ 
        this.orderID = branch.nextOrderID();
        this.orderStatus = orderStatusFlags.NEW;
        this.branchName = branch.getBranchName(); 
        this.orderItems = new ArrayList<>(); 
    }

    public int generateOrderID (){
        return Order.nextOrderID++;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public orderStatusFlags getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(orderStatusFlags Flag){
        if (this.orderStatus != Flag){
            this.orderStatus = Flag;
        }
        else {
            System.out.println("Order is currently"  + this.orderStatus + "already.");
        }
    }

    // wrote another setter and getter for the Flag class ^

    public String checkOrderStatus(){
        if (orderStatus == orderStatusFlags.NEW){
            return "New order.";
        }
        else if (orderStatus == orderStatusFlags.PROCESSED){
            return "Order processed.";
        }
        else if (orderStatus == orderStatusFlags.PICKUP){
            return "Ready to pickup.";
        }
        else return "Completed.";
    }


    // public void placeOrder() throws IOException{
    public void placeOrder(){
        branch.getBranchOrders().addOrder(this);
    }
}