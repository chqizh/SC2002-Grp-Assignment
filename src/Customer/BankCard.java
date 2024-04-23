package Customer;

public class BankCard extends Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public BankCard(){
        super("Bank Card");
    }

    public BankCard(String cardNumber, String expiryDate, String cvv) {
        super("Bank Card");
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        if (this.cardNumber != null && this.expiryDate != null && this.cvv !=null) System.out.println("Processing payment...");
        System.out.println("Bank Card Payment of $" + amount + " processed successfully.");
        return true; 
    }
}
