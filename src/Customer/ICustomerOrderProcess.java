package Customer;

/**
 * This interface defines methods for processing orders by a customer.
 */
public interface ICustomerOrderProcess {

    /**
     * Allows the customer to browse the menu of a specific branch.
     *
     * @param branchName The name of the branch whose menu is to be browsed.
     */
    void browseMenu(String branchName);

    /**
     * Allows the customer to add items to their cart from a specific branch's menu.
     *
     * @param branchName The name of the branch from which items are to be added to the cart.
     */
    void addToCart(String branchName);

    /**
     * Allows the customer to delete items from their cart.
     */
    void deleteFromCart();

    /**
     * Allows the customer to view the items currently in their cart.
     */
    void viewCart();

    /**
     * Places an order for the items currently in the customer's cart from a specific branch.
     *
     * @param branchName The name of the branch from which the order is to be placed.
     */
    void placeOrder(String branchName);

    /**
     * Allows the customer to track the status of their order from a specific branch.
     *
     * @param branchName The name of the branch whose order is to be tracked.
     */
    void trackOrder(String branchName);

    /**
     * Allows the customer to collect their order from a specific branch once it is ready.
     *
     * @param branchName The name of the branch from which the order is to be collected.
     */
    void collectOrder(String branchName);
}
