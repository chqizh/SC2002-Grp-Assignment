package Customer;

public class BankCard extends Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    BankCard(){
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
        System.out.println("Bank Card Payment of $" + amount + " processed successfully.");
        return true; 
    }
}
