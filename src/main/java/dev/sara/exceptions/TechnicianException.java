package dev.sara.exceptions;

public class TechnicianException extends RuntimeException {
    public TechnicianException(String message) {
        super(message);
    }

    public TechnicianException(String message, Throwable cause) {
        super(message, cause);
    }
}
