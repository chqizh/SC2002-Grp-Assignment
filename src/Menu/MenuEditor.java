package Menu;

import java.io.Serializable;
import java.util.*;

public class MenuEditor implements Serializable{
	private ArrayList<MenuItem> menu;
    private transient Scanner sc;

	public MenuEditor(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

	public MenuItem addItems(String branchName){
		sc = new Scanner(System.in);
        int itemId = this.menu.size()+1;

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
                System.out.println("Categories:");
                System.out.println("(1) Ala carte");
                System.out.println("(2) Set Meal");
                System.out.println("(3) Sides");
                System.out.println("(4) Drinks");
                System.out.print("Enter menu item category: ");
                int categoryChoice = sc.nextInt();
                sc.nextLine();

                String category;
                switch (categoryChoice) {
                    case 1:
                        category = "Ala carte";
                        break;
                    case 2:
                        category = "Set Meal";
                        break;
                    case 3:
                        category = "Sides";
                        break;
                    case 4:
                        category = "Drinks";
                        break;
                    default:
                        System.out.println("Invalid category choice. Please enter a number between 1 and 4.");
                        continue;
                }

                MenuItem menuItem = new MenuItem(itemId, name, price, category, branchName);
                return menuItem;
            }
        }
	}

	public void deleteItems() {
        sc = new Scanner(System.in);
        System.out.println("Please enter the Item ID of the item to delete:");
        int id = sc.nextInt();
		sc.nextLine();
        
        boolean found = false;
        for (int i = 0; i < menu.size(); i++) {
            if (this.menu.get(i).getItemID() == id) {
                this.menu.remove(i);
                found = true;
                break; 
            }
        }
        if (found) {
            System.out.println("Item with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
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
