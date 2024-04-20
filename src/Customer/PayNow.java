package Customer;

public class PayNow implements Payment {
    private String mobileNumber;

    public PayNow(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayNow Payment of $" + amount + " processed successfully.");
        return true;
    }
}
