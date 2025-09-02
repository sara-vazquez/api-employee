package dev.sara.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado la solicitud")
public class RequestNotFoundException extends RequestException {

    public RequestNotFoundException(String message) {
        super(message);
    }

}
