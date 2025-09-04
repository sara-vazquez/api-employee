package dev.sara.attendance;

import java.time.LocalDateTime;

public record AttendanceDTOResponse(Long id, LocalDateTime attendedAt, Long request, Long technician) {}
