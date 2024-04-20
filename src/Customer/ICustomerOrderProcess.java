package Customer;

import java.io.IOException;

public interface ICustomerOrderProcess {

    void browseMenu();

    void addToCart();

    void deleteFromCart();

    void viewCart();

    void placeOrder(int branchID)throws IOException;

    void trackOrder(int branchID);
    
}
