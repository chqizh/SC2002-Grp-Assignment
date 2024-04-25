package Customer;

/**
 * Represents a bank card payment method.
 * This class extends the Payment class to handle bank card specific payment processing.
 */
public class BankCard extends Payment {
    private String cardNumber;  // Card number of the bank card
    private String expiryDate;  // Expiry date of the bank card
    private String cvv;         // CVV of the bank card

    /**
     * Constructs a default BankCard instance with default payment description set to "Bank Card".
     */
    public BankCard() {
        super("Bank Card");
    }

    /**
     * Constructs a BankCard with specified details.
     *
     * @param cardNumber The card number of the bank card.
     * @param expiryDate The expiry date of the bank card.
     * @param cvv The CVV of the bank card.
     */
    public BankCard(String cardNumber, String expiryDate, String cvv) {
        super("Bank Card");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    /**
     * Processes a payment using the bank card details.
     *
     * @param amount The amount to be paid.
     * @return true if the payment was processed successfully, false otherwise.
     */
    @Override
    public boolean processPayment(double amount) {
        if (this.cardNumber == null || this.expiryDate == null || this.cvv == null) {
            System.out.println("Payment failed: Incomplete card details.");
            return false;
        }
        System.out.println("Processing payment...");
        System.out.println("Connecting to the bank server...");
        simulateNetworkLatency();
        System.out.printf("Bank Card Payment of $%.2f processed successfully.\n", amount);
        return true; 
    }
    
    /**
     * Simulates network latency during the connection to the payment server.
     */
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2000);  // Simulate 2 seconds of network delay
            System.out.println("Connected.");
            Thread.sleep(2000);  // Simulate 2 seconds of delay
        } catch (InterruptedException e) {
            System.out.println("Network error.");
        }
    }
}
    
