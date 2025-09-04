package dev.sara.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado la solicitud")
public class AttendanceNotFoundException extends AttendanceException {
    public AttendanceNotFoundException(String message) {
        super(message);
    }
}
