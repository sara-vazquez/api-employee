package dev.sara.exceptions;

public class TopicException extends RuntimeException{
    
    public TopicException(String message) {
        super(message);
    }

    public TopicException(String message, Throwable cause) {
        super(message, cause);
    }
}
