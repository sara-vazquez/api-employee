package dev.sara.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado el tema")
public class TopicNotFoundException extends TopicException {

    public TopicNotFoundException(String message) {
        super(message);
    }
    
}
