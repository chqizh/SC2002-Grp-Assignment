package Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuDisplay {
    //@SuppressWarnings("unchecked")
    private ArrayList<MenuItem> menu;
    private transient Scanner sc;

    public MenuDisplay(){
        menu = new ArrayList<>();
    }

    public MenuDisplay(Menu branchMenu){
        menu = branchMenu.getMenuItemsList();
    }
    
	public void displayMenu() {
        sc = new Scanner(System.in);

        // Deserialize the menu from the file
        // try {
        //     menu = (ArrayList<MenuItem>) SerializationUtil.deserialize("menu.ser");
        //     //System.out.println("Menu deserialized successfully.");
        // } catch (IOException | ClassNotFoundException e) {
        //     //System.err.println("Deserialization failed: " + e.getMessage());
        //     return; // Exit the method or handle the error accordingly
        // }
        
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
