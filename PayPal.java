
public class PayPal implements PaymentMethod {
	private String email;
	
	// constructor
    public PayPal(String email) {
        this.email = email;
    }

    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        String msg = String.format("%.2f paid by PayPal using %s, and the delivery address is %s.", 
                                   amount, email, fullAddress.toString());
        return new Receipt(msg);
    }
}
