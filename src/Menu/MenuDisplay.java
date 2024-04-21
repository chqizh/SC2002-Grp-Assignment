package Menu;

import java.io.*;
import java.util.ArrayList;
import DataPersistence.SerializationUtil;

public class MenuDisplay {
    //@SuppressWarnings("unchecked")
    private ArrayList<MenuItem> menu;
    
	public void displayMenu() {
        // Deserialize the menu from the file
        // try {
        //     menu = (ArrayList<MenuItem>) SerializationUtil.deserialize("menu.ser");
        //     //System.out.println("Menu deserialized successfully.");
        // } catch (IOException | ClassNotFoundException e) {
        //     //System.err.println("Deserialization failed: " + e.getMessage());
        //     return; // Exit the method or handle the error accordingly
        // }
        
        // Display header columns
        System.out.printf("%-8s %-20s %-10s %-15s\n", "Item ID", "Name", "Price", "Category");
        System.out.println("------------------------------------------------------------");

        // Display menu items
        //ArrayList<MenuItem> menuItems = menu.getMenuItems();
        for (MenuItem menuItem : menu) {
            System.out.printf("%-8d %-20s $%-10.2f %-15s\n", 
                              menuItem.getItemID(), 
                              menuItem.getItemName(), 
                              menuItem.getPrice(), 
                              menuItem.getCategory());
        }
    }
}
