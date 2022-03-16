package exceptions;

public class StaffExistsException extends RuntimeException {
    String message = "";

    public StaffExistsException (String message) {
        super(message);
    }


}
