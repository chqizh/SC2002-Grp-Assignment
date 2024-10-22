package Customer;

/**
 * Represents a PayPal payment method.
 * This class extends the Payment class to handle PayPal-specific payment processing.
 */
public class PayPal extends Payment {
    private String email;       // Email associated with the PayPal account
    private String password;    // Password for the PayPal account

    /**
     * Constructs a default PayPal payment method with the description set to "Paypal".
     */
    public PayPal() {
        super("Paypal");
    }

    /**
     * Constructs a PayPal payment method with specified email and password.
     *
     * @param email The email associated with the PayPal account.
     * @param password The password for the PayPal account.
     */
    public PayPal(String email, String password) {
        super("Paypal");
        this.email = email;
        this.password = password;
    }

    /**
     * Processes a payment using PayPal.
     *
     * @param amount The amount to be paid.
     * @return true if the payment was processed successfully, false otherwise.
     */
    @Override
    public boolean processPayment(double amount) {
        if (this.email == null || this.password == null) {
            System.out.println("Payment failed: PayPal account details are incomplete.");
            return false;
        }
        System.out.println("Processing payment...");
        simulateNetworkLatency();
        System.out.printf("PayPal Payment of $%.2f processed successfully.\n", amount);
        return true;
    }

    /**
     * Simulates network latency during the connection to the PayPal server.
     */
    private void simulateNetworkLatency() {
        try {
            System.out.print("Connecting to PayPal.");
            Thread.sleep(1000);  // Simulate 1 seconds of network delay
            System.out.print(".");
            Thread.sleep(1000);  // Simulate 1 seconds of network delay
            System.out.print(".\n");
            Thread.sleep(1000);  // Simulate 1 seconds of network delay
            System.out.println("Connected to PayPal.");
            Thread.sleep(2000);  // Simulate 2 seconds of delay
        } catch (InterruptedException e) {
            System.out.println("Network error during PayPal connection.");
        }
    }
}
