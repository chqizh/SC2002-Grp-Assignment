package Customer;

import java.io.Serializable;

/**
 * Abstract class representing a payment method.
 */
public abstract class Payment implements Serializable {
    /** The name of the payment method. */
    public String paymentMethodName;

    /**
     * Constructs a Payment object with a specified name.
     *
     * @param name The name of the payment method.
     */
    public Payment(String name){
        this.paymentMethodName = name;
    }

    /**
     * Process the payment with a specified amount.
     *
     * @param amount The amount to process.
     * @return True if the payment was successful, false otherwise.
     */
    public abstract boolean processPayment(double amount);

    /**
     * Sets the name of the payment method.
     *
     * @param name The name to set.
     */
    public void setPaymentMethodName(String name){
        paymentMethodName = name;
    };

    /**
     * Gets the name of the payment method.
     *
     * @return The name of the payment method.
     */
    public String getPaymentMethodName(){
        return paymentMethodName;
    }
}
