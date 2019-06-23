package exceptions;

public class OutOfNumberFormatException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public OutOfNumberFormatException(String message) {
        super(message);
    }

}
