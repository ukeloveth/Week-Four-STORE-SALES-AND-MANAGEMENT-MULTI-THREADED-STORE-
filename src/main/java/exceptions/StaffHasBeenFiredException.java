package exceptions;

public class StaffHasBeenFiredException extends RuntimeException{
    String message = "";
    public StaffHasBeenFiredException (String message) {
        super(message);
    }
}
