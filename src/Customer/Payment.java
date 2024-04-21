package Customer;

public abstract class Payment {
    public String paymentMethodName;

    public abstract boolean processPayment(double amount);

    public void setPaymentMethodName(String name){
        paymentMethodName = name;
    };

    public abstract String getPaymentMethodName();
}
