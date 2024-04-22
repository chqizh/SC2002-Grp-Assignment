package Customer;

// import java.io.IOException;

public interface ICustomerOrderProcess {

    void browseMenu(String branchName);

    void addToCart(String branchName);

    void deleteFromCart();

    void viewCart();

    // void placeOrder(int branchID)throws IOException;
    void placeOrder(String branchName);
    // void trackOrder(int branchID)throws IOException;
    void trackOrder(String branchName);

}
