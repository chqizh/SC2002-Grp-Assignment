package Customer;

public class Paypal extends Payment {
    private String email;
    private String password;

    Paypal(){
        setPaymentMethodName("Paypal");
    }
    
    public String getPaymentMethodName(){
        return paymentMethodName;
    }

    public Paypal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayPal Payment of $" + amount + " processed successfully.");
        return true;
    }
}