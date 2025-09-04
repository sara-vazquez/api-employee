package dev.sara.attendance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sara.implementations.IAttendanceService;

@RestController
@RequestMapping(path = "${api-endpoint}/attendances")
public class AttendanceController {

    private final IAttendanceService service;

    public AttendanceController(IAttendanceService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<AttendanceDTOResponse> markAsAttended(@RequestBody AttendanceDTORequest dtoRequest) {
        if (dtoRequest.attendedAt() == null || dtoRequest.request() == null || dtoRequest.technician() == null) {
        return ResponseEntity.badRequest().build();
        }

        AttendanceDTOResponse response = service.markAsAttended(dtoRequest);

        if (response == null) {
        return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(201).body(response);
    }
}
