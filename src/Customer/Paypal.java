package Customer;

/**
 * Represents a PayPal payment method.
 * This class extends the Payment class to handle PayPal-specific payment processing.
 */
public class Paypal extends Payment {
    private String email;       // Email associated with the PayPal account
    private String password;    // Password for the PayPal account

    /**
     * Constructs a default PayPal payment method with the description set to "Paypal".
     */
    public Paypal() {
        super("Paypal");
    }

    /**
     * Constructs a PayPal payment method with specified email and password.
     *
     * @param email The email associated with the PayPal account.
     * @param password The password for the PayPal account.
     */
    public Paypal(String email, String password) {
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
        System.out.println("Connecting to PayPal...");
        simulateNetworkLatency();
        System.out.println("PayPal Payment of $" + amount + " processed successfully.");
        return true;
    }

    /**
     * Simulates network latency during the connection to the PayPal server.
     */
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2000);  // Simulate 2 seconds of network delay
            System.out.println("Connected to PayPal.");
        } catch (InterruptedException e) {
            System.out.println("Network error during PayPal connection.");
        }
    }
}
