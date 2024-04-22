package Customer;

public class Paypal extends Payment {
    private String email;
    private String password;

    Paypal(){
        super("Paypal");
    }

    public Paypal(String email, String password) {
        super("Paypal");
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("PayPal Payment of $" + amount + " processed successfully.");
        return true;
    }
}