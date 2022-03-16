package exceptions;

public class ApplicationDeclinedException extends  RuntimeException{
    String message = "";

    public ApplicationDeclinedException(String message){
        super(message);
    }
}
