package dev.sara.globalHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.sara.exceptions.RequestNotFoundException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> handleNotFound(RequestNotFoundException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
    
}
