package Accounts;

import Branch.*;

public interface IOrderProcess {

	/**
	 * 
	 * @param orderID
	 */
	public void processOrders(Branch branch, int orderID);

	public void viewNewOrders(Branch branch);

	/**
	 * 
	 * @param orderID
	 */
	public void viewOrder(Branch branch, int orderID);
	


}
