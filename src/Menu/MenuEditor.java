package Menu;

import java.io.Serializable;
import java.util.*;


/**
 * This class is responsible for editing menu items, including adding new items,
 * deleting existing items, and updating item names and prices.
 */
public class MenuEditor implements Serializable{
	private ArrayList<MenuItem> menu;
    private transient Scanner sc;
    private Menu menuClass;

    /**
     * Constructor for the MenuEditor class that initializes it with the menu from the provided Menu class instance.
     *
     * @param menuClass The Menu class instance from which the menu items will be managed.
     */
    public MenuEditor(Menu menuClass){
        this.menuClass = menuClass;
        this.menu = menuClass.getMenuItemsList();
    }

    /**
     * Adds a new item to the menu after taking input from the user. Ensures that no duplicate names exist.
     *
     * @param branchName The name of the branch for which the menu item is being added.
     * @return true if the item was added successfully, false if a duplicate name was found.
     */
	public boolean addItems(String branchName){
		sc = new Scanner(System.in);
        int itemId = menuClass.getMaxItemID();

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
                double price = -1;
                do {
                    try {
                        System.out.print("Enter menu item price: ");
                        price = sc.nextDouble();
                        sc.nextLine();
                    }
                    catch (Exception e){
                        sc.nextLine();
                        System.out.println("Invalid price entered.");
                    }
                    if (price < 0) System.out.println("Invalid price entered.");
                } while (price < 0);

                int categoryChoice = 0;
                do {
                    System.out.println("Categories:");
                    System.out.println("(1) Ala carte");
                    System.out.println("(2) Set Meal");
                    System.out.println("(3) Sides");
                    System.out.println("(4) Drinks");
                    System.out.print("Enter menu item category: ");
                    try {
                        categoryChoice = sc.nextInt();
                        sc.nextLine();
                    }
                    catch (Exception e){
                        sc.nextLine();
                        System.out.println("Invalid option number entered.");
                    }
                    if (categoryChoice < 1 || categoryChoice > 4) System.out.println("Invalid option entered.");
                } while (categoryChoice < 1 || categoryChoice > 4);

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

                this.menu.add(new MenuItem(itemId, name, price, category, branchName));
                System.out.printf("Successfully added %s item to menu.\n", name);
                return true;
            }
            else {
                System.out.printf("Failed to add %s item to menu.\n", name);
                return false;
            }
        }
	}

    /**
     * Deletes an item from the menu by item ID.
     */
	public void deleteItems() {
        sc = new Scanner(System.in);
        System.out.println("Please enter the Item ID of the item to delete:");
        int id;
        try {
            id = sc.nextInt();
		    sc.nextLine();
        }
        catch (Exception e){
            sc.nextLine();
            System.out.println("Invalid item ID entered.");
            return;
        }
        
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

    /**
     * Updates the name of a menu item.
     *
     * @param id The ID of the menu item to be updated.
     * @param newName The new name for the menu item.
     * @param menu The menu list where the item resides.
     */
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

    /**
     * Updates the price of a menu item.
     *
     * @param id The ID of the menu item to be updated.
     * @param newPrice The new price for the menu item.
     * @param menu The menu list where the item resides.
     */
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
