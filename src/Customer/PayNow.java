package Customer;

public class PayNow extends Payment {
    private String mobileNumber;

    public PayNow(){
        super("PayNow");
    }

    public PayNow(String mobileNumber) {
        super("PayNow");
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayNow Payment of $" + amount + " processed successfully.");
        return true;
    }
}
