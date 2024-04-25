package Menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for displaying the menu items in a formatted manner.
 */
public class MenuDisplay {
    private ArrayList<MenuItem> menu;
    private transient Scanner sc;

    /**
     * Default constructor that initializes the menu display with an empty list of menu items.
     */
    public MenuDisplay(){
        menu = new ArrayList<>();
    }

    /**
     * Overloaded constructor that initializes the menu display with a specific branch's menu.
     *
     * @param branchMenu The menu object containing the list of menu items to display.
     */
    public MenuDisplay(Menu branchMenu){
        menu = branchMenu.getMenuItemsList();
    }
    
    /**
     * Displays the menu items in a formatted table.
     */
	public void displayMenu() {
        sc = new Scanner(System.in);
        
        // Display header columns
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-8s %-25s %-11s %-15s\n", "Item ID", "Name", "Price", "Category");
        System.out.println("------------------------------------------------------------");

        // Display menu items
        //ArrayList<MenuItem> menuItems = menu.getMenuItems();
        for (MenuItem menuItem : menu) {
            System.out.printf("%-8d %-25s $%-10.2f %-15s\n", 
                              menuItem.getItemID(), 
                              menuItem.getItemName(), 
                              menuItem.getPrice(), 
                              menuItem.getCategory());
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Press any key to continue.");
        sc.nextLine();
    }
}
