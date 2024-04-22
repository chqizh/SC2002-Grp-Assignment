package Customer;

public abstract class Payment {
    public String paymentMethodName;

    public Payment(String name){
        this.paymentMethodName = name;
    }

    public abstract boolean processPayment(double amount);

    public void setPaymentMethodName(String name){
        paymentMethodName = name;
    };

    public  String getPaymentMethodName(){
        return paymentMethodName;
    }
}
