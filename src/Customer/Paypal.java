package Customer;

public class Paypal implements Payment {
    private String email;
    private String password;

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