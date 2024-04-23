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
        if (this.password != null && this.email != null) System.out.println("Processing payment...");
        System.out.println("PayPal Payment of $" + amount + " processed successfully.");
        return true;
    }


}