package Branch;

import java.io.*;

public class Menu {
    private String branchID;
    private static MenuItem menu[];
    private static int numItems;

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchID() {
        return branchID;
    }

    public static void setMenu(MenuItem[] menu) {
        //TODO
        Menu.menu = menu;
    }

    public static MenuItem[] getMenu() {
        return menu;
    }

    public static void printMenu(){
        //TODO
        for (int i = 0; i < numItems; i++){
            System.out.printf("Item: %s, Price: %f", menu[i].getItemName(), menu[i].getPrice() );
        }
    }
}
