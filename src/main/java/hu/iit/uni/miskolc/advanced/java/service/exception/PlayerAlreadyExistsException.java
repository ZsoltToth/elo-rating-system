package hu.iit.uni.miskolc.advanced.java.service.exception;

public class PlayerAlreadyExistsException extends Exception {

    public PlayerAlreadyExistsException() {
    }

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    public PlayerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyExistsException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
