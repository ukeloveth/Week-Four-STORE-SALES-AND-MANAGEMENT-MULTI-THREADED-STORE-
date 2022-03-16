package exceptions;

public class NoStaffToFireException extends RuntimeException{
    String message = "";

    public NoStaffToFireException (String message) {
        super(message);
    }
}
