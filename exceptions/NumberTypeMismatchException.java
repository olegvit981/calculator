package exceptions;

public class NumberTypeMismatchException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public NumberTypeMismatchException(String message) {
        super(message);
    }

}
