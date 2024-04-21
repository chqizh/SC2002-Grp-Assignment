package Customer;

public class PayNow extends Payment {
    private String mobileNumber;

    PayNow(){
        setPaymentMethodName("Paynow");
    }

    public String getPaymentMethodName(){
        return paymentMethodName;
    }

    public PayNow(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayNow Payment of $" + amount + " processed successfully.");
        return true;
    }
}
