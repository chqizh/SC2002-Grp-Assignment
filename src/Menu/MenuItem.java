package Menu;

import java.io.Serializable;

/**
 * This class represents a single item in the menu, containing all relevant details
 * such as item name, price, ID, category, associated branch, and customizations.
 */
public class MenuItem implements Serializable{
    
    private String itemName;
    private double price;
    private int itemID;
    private String category;
    private String branch;
    private String customisation;

    /**
     * Constructs a MenuItem with the specified details.
     * 
     * @param itemID the unique identifier for the menu item
     * @param itemName the name of the menu item
     * @param price the price of the menu item
     * @param category the category of the menu item (e.g., appetizer, main course, dessert)
     * @param branch the name of the branch to which the menu item belongs
     */
    public MenuItem(int itemID, String itemName, double price, String category, String branch){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.branch = branch;
        this.customisation = "-";
    }

    /**
     * Gets the category of this menu item.
     * 
     * @return the category of the menu item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of this menu item.
     * 
     * @param category the new category for the menu item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the name of the branch associated with this menu item.
     * 
     * @return the name of the branch
     */
    public String getBranchName() {
        return branch;
    }

    /**
     * Sets the name of the branch associated with this menu item.
     * 
     * @param branch the new name of the branch
     */
    public void setBranchName(String branch) {
        this.branch = branch;
    }

    /**
     * Sets the name of this menu item.
     * 
     * @param itemName the new name of the menu item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the name of this menu item.
     * 
     * @return the name of the menu item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the price of this menu item.
     * 
     * @param price the new price of the menu item
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * Gets the price of this menu item.
     * 
     * @return the price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the unique identifier for this menu item.
     * 
     * @return the item ID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets the unique identifier for this menu item.
     * 
     * @param itemID the new identifier for the menu item
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the customisation options for this menu item.
     * 
     * @return the customisation options
     */
    public String getCustomisation() {
        return customisation;
    }

    /**
     * Sets the customisation options for this menu item.
     * 
     * @param customisation the new customisation options for the menu item
     */
    public void setCustomisation(String customisation) {
        this.customisation = customisation;
    }
}
