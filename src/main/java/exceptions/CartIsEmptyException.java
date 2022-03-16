package exceptions;

public class CartIsEmptyException extends  RuntimeException{
    String message = "";

    public CartIsEmptyException(String message){
        super(message);
    }
}
