
public class CreditCard implements PaymentMethod {
	private String cardNumber;
    private String securityCode;

    // constructor
    public CreditCard(String cardNumber, String securityCode) {
        this.cardNumber = cardNumber;
        this.setSecurityCode(securityCode);
    }

    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        String msg = String.format("%.2f paid by Credit Card using %s, and the delivery address is %s.", 
                                   amount, cardNumber, fullAddress.toString());
        return new Receipt(msg);
    }




}
