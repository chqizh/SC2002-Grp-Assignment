package Menu;

import java.io.Serializable;
import java.util.*;

public class EditMenu implements Serializable{
	private ArrayList<MenuItem> menu;
    private String branchName;
    private transient Scanner sc;

	public EditMenu(ArrayList<MenuItem> menu, String branchName) {
		this.menu = menu;
        this.branchName = branchName;
	}

	public MenuItem addItems(){
		sc = new Scanner(System.in);
		System.out.print("Enter item ID: ");
		int itemId = sc.nextInt();
		sc.nextLine();

        while (true) {
            System.out.print("Enter menu item name: ");
            String name = sc.nextLine();

            // Check for duplicate names
            boolean duplicateFound = false;
            for (MenuItem item : menu) {
                if (item.getItemName().equalsIgnoreCase(name)) {
                    System.out.println("Error! Menu item with the same name already exists.");
                    duplicateFound = true;
                    break;
                }
            }

            if (!duplicateFound) {
                System.out.print("Enter menu item price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter menu item category: ");
                String category = sc.nextLine();

                MenuItem menuItem = new MenuItem(itemId, name, price, category, this.branchName);
                return menuItem;
            }
        }
	}

	public ArrayList<MenuItem> deleteItems(ArrayList<MenuItem>menu) {
        sc = new Scanner(System.in);
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
			// System.out.println("Current ID: "+menuItem.getItemID());
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
