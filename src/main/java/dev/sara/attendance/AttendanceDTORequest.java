package dev.sara.attendance;

import java.time.LocalDateTime;

public record AttendanceDTORequest(LocalDateTime attendedAt, Long request, Long technician) {
    
}
