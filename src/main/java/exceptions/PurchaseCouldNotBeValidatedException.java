package exceptions;

public class PurchaseCouldNotBeValidatedException extends RuntimeException{
    String message = "";

    public PurchaseCouldNotBeValidatedException (String message) {
        super(message);
    }
}
