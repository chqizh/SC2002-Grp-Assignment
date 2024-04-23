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
        if (this.mobileNumber != null) System.out.println("Processing payment...");
        System.out.println("PayNow Payment of $" + amount + " processed successfully.");
        return true;
    }
}
