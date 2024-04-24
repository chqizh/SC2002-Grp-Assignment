package Customer;

import java.io.Serializable;

public abstract class Payment implements Serializable{
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
