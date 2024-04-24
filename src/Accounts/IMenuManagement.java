package Accounts;

import Branch.*;

/**
 * Interface defining menu management capabilities for branches.
 * Implementing classes will be able to view, add, remove, and edit menu items.
 */
public interface IMenuManagement {

    /**
     * Displays the menu for a specified branch.
     * 
     * @param branch The branch whose menu is to be displayed.
     */
    public void viewMenu(Branch branch);

    /**
     * Adds a new menu item to the menu of a specified branch.
     * 
     * @param branch The branch to which a new menu item will be added.
     */
    public void addMenuItem(Branch branch);

    /**
     * Removes a menu item from the menu of a specified branch.
     * 
     * @param branch The branch from which a menu item will be removed.
     */
    public void removeMenuItem(Branch branch);

    /**
     * Edits an existing menu item in the menu of a specified branch.
     * 
     * @param branch The branch whose menu item is to be edited.
     */
    public void editMenuItem(Branch branch);

}
