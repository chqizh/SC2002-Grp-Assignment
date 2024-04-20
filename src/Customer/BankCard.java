package Customer;

public class BankCard implements Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public BankCard(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Bank Card Payment of $" + amount + " processed successfully.");
        return true; 
    }
}
