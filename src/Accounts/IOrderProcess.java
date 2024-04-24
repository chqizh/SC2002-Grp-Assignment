package Accounts;

import Branch.*;

/**
 * Interface for processing and managing orders within a branch.
 */
public interface IOrderProcess {

    /**
     * Processes an order based on the given order ID within a branch context.
     * The specific processing logic will be implemented by the class that
     * implements this interface.
     *
     * @param branch The branch where the order needs to be processed.
     * @param orderID The unique identifier of the order to process.
     */
    public void processOrders(Branch branch, int orderID);

    /**
     * Displays a list of new orders that have not yet been processed for a given branch.
     *
     * @param branch The branch whose new orders are to be displayed.
     */
    public void viewNewOrders(Branch branch);

    /**
     * Views the details of a specific order in a branch using the order ID.
     *
     * @param branch The branch where the order is located.
     * @param orderID The unique identifier of the order to view.
     */
    public void viewOrder(Branch branch, int orderID);
}
