package Menu;

import java.io.*;
import java.util.*;
import Branch.Branch;

/**
 * This class represents a menu for a specific branch. It allows for the addition, deletion, 
 * and updating of menu items.
 */
public class Menu implements Serializable{
    private ArrayList<MenuItem> menu;
    private MenuEditor editMenu;
    private String branchName;
    private transient Scanner sc;
    private int maxItemID;

    /**
     * Constructs a Menu object for a given branch.
     * 
     * @param branchName the name of the branch this menu belongs to.
     */
    public Menu(String branchName) {
        this.menu = new ArrayList<MenuItem>();
        this.editMenu = new MenuEditor(this);
        this.maxItemID = 0;
    }

    /**
     * Adds multiple new items to the menu by interacting with the user.
     * 
     * @return true if items were added successfully.
     * @throws IOException if an I/O error occurs.
     */
    public boolean addItems() throws IOException {
        sc = new Scanner(System.in);
        System.out.print("Enter the number of items you would like to add: ");
        int numberToAdd = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numberToAdd; i++) {
            (this.editMenu).addItems(this.branchName);
        }
        return true;
    }

    /**
     * Adds a single new item to the menu.
     * 
     * @param menuItem the MenuItem object to be added.
     * @return true if the item was added successfully, false if a duplicate was found.
     */
    public boolean addItems(MenuItem menuItem){
        // Prevents adding an item with a duplicate ID or name.
        for (MenuItem currentItem : this.menu){
            if (currentItem.getItemID() == menuItem.getItemID() || 
                currentItem.getItemName().equalsIgnoreCase(menuItem.getItemName())){
                System.out.println("Duplicate item name or itemID found in menu.");
                return false;
            }
        }
        if (this.menu.add(menuItem)){
            this.maxItemID = menuItem.getItemID();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes multiple items from the menu by interacting with the user.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public void deleteItems() throws IOException {
        sc = new Scanner(System.in);
        System.out.println("How many menu items would you like to delete?");
        int numberToDelete = sc.nextInt();
        sc.nextLine();
        
        for (int i=0; i < numberToDelete; i++) {
            this.editMenu.deleteItems();
        }
    }

    /**
     * Updates the name of a specific item in the menu.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public void updateName() throws IOException{
        sc = new Scanner(System.in);
        System.out.print("Enter ID of item: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Name: ");
        String newName = sc.nextLine();

        editMenu.updateName(itemId, newName, menu);
    }

    /**
     * Updates the price of a specific item in the menu.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public void updatePrice() throws IOException{
        sc = new Scanner(System.in);
        System.out.print("Enter ID of Item: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Price: ");
        float newprice = sc.nextFloat();
        sc.nextLine();

        editMenu.updatePrice(itemId, newprice, menu);
    }

    /**
     * Retrieves the list of menu items.
     * 
     * @return a list of MenuItem objects.
     */
    public ArrayList<MenuItem> getMenuItemsList() {
        return menu;
    }

    /**
     * Displays the menu for a given branch.
     * 
     * @param branch the branch whose menu is to be displayed.
     */
    public void displayMenu(Branch branch){
        MenuDisplay display = new MenuDisplay(branch.getBranchMenu());
        display.displayMenu();
    }

    /**
     * Sets the branch name associated with this menu.
     * 
     * @param branchName the name of the branch.
     */
    public void setBranchName(String branchName){
        this.branchName = branchName;
    }

    /**
     * Gets the branch name associated with this menu.
     * 
     * @return the name of the branch.
     */
    public String getBranchName(){
        return this.branchName;
    }

    /**
     * Gets the maximum item ID used in this menu. It increments the ID each time it is called.
     * 
     * @return the maximum item ID.
     */
    public int getMaxItemID(){
        return ++this.maxItemID;
    }
}
