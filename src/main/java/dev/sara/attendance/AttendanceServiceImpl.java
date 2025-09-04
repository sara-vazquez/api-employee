package dev.sara.attendance;

import org.springframework.stereotype.Service;

import dev.sara.exceptions.RequestException;
import dev.sara.exceptions.TechnicianNotFoundException;
import dev.sara.implementations.IAttendanceService;
import dev.sara.request.RequestEntity;
import dev.sara.request.RequestRepository;
import dev.sara.technician.TechnicianEntity;
import dev.sara.technician.TechnicianRepository;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final RequestRepository requestRepository;
    private final TechnicianRepository technicianRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, RequestRepository requestRepository, TechnicianRepository technicianRepository) {
        this.attendanceRepository = attendanceRepository;
        this.requestRepository = requestRepository;
        this.technicianRepository = technicianRepository;
    }

    @Override
    public AttendanceDTOResponse markAsAttended(AttendanceDTORequest dtoRequest) {
        RequestEntity request = requestRepository.findById(dtoRequest.request())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (request.isAttended()) {
            throw new RequestException("La solicitud ya está atendida");
        }

        TechnicianEntity technician = technicianRepository.findById(dtoRequest.technician())
                .orElseThrow(() -> new TechnicianNotFoundException("Técnico no encontrado"));

        AttendanceEntity attendance = AttendanceMapper.toEntity(dtoRequest, request, technician);

        AttendanceEntity savedAttendance = attendanceRepository.save(attendance);

        request.setAttended(true);
        request.setUpdatedAt(dtoRequest.attendedAt());
        requestRepository.save(request);

        return AttendanceMapper.toDTO(savedAttendance);
    }

    
}
