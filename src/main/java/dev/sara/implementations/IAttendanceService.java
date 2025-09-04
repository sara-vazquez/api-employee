package dev.sara.implementations;

import dev.sara.attendance.AttendanceDTORequest;
import dev.sara.attendance.AttendanceDTOResponse;

public interface IAttendanceService {
    AttendanceDTOResponse markAsAttended(AttendanceDTORequest dtoRequest);
}
