package Menu;

import java.io.Serializable;
import java.util.*;

public class EditMenu implements Serializable{
	public ArrayList<MenuItem> menu;
	//public MenuItem menu_item;
	public EditMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

	public MenuItem addItems(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter item ID:");
		int itemId = sc.nextInt();
		sc.nextLine();

	    System.out.println("Enter menu item name:");
	    String name = sc.nextLine();
		
		System.out.println("Enter menu item price:");
	   	float price = sc.nextFloat();
		sc.nextLine();
		
		System.out.println("Enter menu item branch:");
	    String branch = sc.nextLine();
		
		System.out.println("Enter menu item category:");
	    String category = sc.nextLine();
		
		MenuItem menu_item = new MenuItem(itemId, name, price, category, branch);
		return menu_item;

	}

	public ArrayList<MenuItem> deleteItems(ArrayList<MenuItem>menu) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Item ID of the item to delete:");
        int id = sc.nextInt();
		sc.nextLine();
        
        boolean found = false;
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getItemID() == id) {
                menu.remove(i);
                found = true;
                break; 
            }
        }
        if (found) {
            System.out.println("Item with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
        
        return menu; // Return the updated menu
    }

	public void updateName(int id, String newName, ArrayList<MenuItem> menu) {
        boolean found = false;
        for (MenuItem menuItem : menu) {
			System.out.println("Current ID: "+menuItem.getItemID());
            if (menuItem.getItemID() == id) {
                menuItem.setItemName(newName);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            System.out.println("Name updated successfully.");
            
        }
    }
	public void updatePrice(int id, float newPrice, ArrayList<MenuItem> menu) {
        boolean found = false;
        for (MenuItem menuItem : menu) {
            if (menuItem.getItemID() == id) {
                menuItem.setPrice(newPrice);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            System.out.println("Price updated successfully.");
        }
    }
 }
