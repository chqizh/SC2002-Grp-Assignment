package Customer;

/**
 * Represents a PayNow payment method.
 * This class extends the Payment class to handle mobile-based PayNow payment processing.
 */
public class PayNow extends Payment {
    private String mobileNumber;  // Mobile number associated with the PayNow account

    /**
     * Constructs a default PayNow payment method with the description set to "PayNow".
     */
    public PayNow() {
        super("PayNow");
    }

    /**
     * Constructs a PayNow payment method with a specified mobile number.
     *
     * @param mobileNumber The mobile number associated with the PayNow account.
     */
    public PayNow(String mobileNumber) {
        super("PayNow");
        this.mobileNumber = mobileNumber;
    }

    /**
     * Processes a payment using the PayNow service.
     *
     * @param amount The amount to be paid.
     * @return true if the payment was processed successfully, false if mobile number is missing.
     */
    @Override
    public boolean processPayment(double amount) {
        if (this.mobileNumber == null) {
            System.out.println("Payment failed: Mobile number is required for PayNow.");
            return false;
        }
        System.out.println("Processing payment...");
        System.out.println("Connecting to PayNow service...");
        simulateNetworkLatency();
        System.out.println("PayNow Payment of $" + amount + " processed successfully.");
        return true;
    }

    /**
     * Simulates network latency during the connection to the PayNow service.
     */
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2000);  // Simulate 2 seconds of network delay
            System.out.println("Connected to PayNow.");
        } catch (InterruptedException e) {
            System.out.println("Network error during PayNow connection.");
        }
    }
}
