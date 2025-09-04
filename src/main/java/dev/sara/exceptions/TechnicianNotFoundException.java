package dev.sara.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado el técnico")
public class TechnicianNotFoundException extends TechnicianException {
    
    public TechnicianNotFoundException(String message) {
        super(message);
    }
}
