package Branch;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String branchID;
    //private static MenuItem menu[];
    private static List<MenuItem> menu;
    private static int numItems;

    public Menu(){
        this.menu = new ArrayList<>();
    }


    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchID() {
        return branchID;
    }

    public void printMenuItems() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-10s |%n", "Item ID", "Name", "Price");
        System.out.println("---------------------------------------------------------------");

        // Print menu items
        for (MenuItem item : menu) {
            System.out.printf("| %-10d | %-30s | $%-9.2f |%n", item.getItemID(), item.getItemName(), item.getPrice());
        }

        // Print table footer
        System.out.println("----------------------------------------------------------------");
    }

    public void addMenuItem(MenuItem menuItem) {
        menu.add(menuItem);
    }

    public void removeMenuItem(int itemId) {
        int index = -1;
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getItemID() == itemId) {
                index = i;
                break;
            }
        }
        // Remove the item if found
        if (index != -1) {
            menu.remove(index);
        }
    }
}