package Accounts;

//import java.util.*;
import Branch.*;
//import Customer.*;

public interface IOrderProcess {

	/**
	 * 
	 * @param orderID
	 */
	void processOrders(Branch branch, int orderID);

	void viewNewOrders(Branch branch);

	/**
	 * 
	 * @param orderID
	 */
	void viewOrder(Branch branch, int orderID);
	


}
