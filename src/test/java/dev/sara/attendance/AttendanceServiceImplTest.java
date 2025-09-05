package dev.sara.attendance;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.sara.exceptions.RequestException;
import dev.sara.request.RequestEntity;
import dev.sara.request.RequestRepository;
import dev.sara.technician.TechnicianEntity;
import dev.sara.technician.TechnicianRepository;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceImplTest {
    
    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private RequestRepository requestRepository;
    
    @Mock
    private TechnicianRepository technicianRepository;

    @Test
    void testMarkAsAttended_ShouldCreateAttendanceAndUpdateRequest() {
        Long requestId = 1L;
        Long technicianId = 1L;
        LocalDateTime now = LocalDateTime.now();

        RequestEntity request = new RequestEntity();
        request.setId(requestId);
        request.setAttended(false);

        TechnicianEntity technician = new TechnicianEntity();
        technician.setId(technicianId);
        technician.setTechnicianName("Michael Scott");

        AttendanceDTORequest dtoRequest = new AttendanceDTORequest(now, requestId, technicianId);

        AttendanceEntity savedAttendance = new AttendanceEntity();
        savedAttendance.setId(10L);
        savedAttendance.setAttendedAt(now);
        savedAttendance.setRequest(request);
        savedAttendance.setTechnician(technician);

        when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));
        when(technicianRepository.findById(technicianId)).thenReturn(Optional.of(technician));
        when(attendanceRepository.save(Mockito.any(AttendanceEntity.class))).thenReturn(savedAttendance);
        when(requestRepository.save(Mockito.any(RequestEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AttendanceDTOResponse result = attendanceService.markAsAttended(dtoRequest);

        assertThat(result.id(), is(equalTo(10L)));
        assertThat(result.attendedAt(), is(equalTo(now)));
        assertThat(result.request(), is(equalTo(requestId)));
        assertThat(result.technician(), is(equalTo(technicianId)));

        verify(requestRepository).findById(requestId);
        verify(technicianRepository).findById(technicianId);
        verify(attendanceRepository).save(Mockito.any(AttendanceEntity.class));
        verify(requestRepository).save(request);

        assertThat(request.isAttended(), is(true));
    }

    @Test
    void testMarkAsAttended_WhenRequestAlreadyAttended() {
        Long requestId = 2L;
        Long technicianId = 1L;
        LocalDateTime now = LocalDateTime.now();

        RequestEntity request = new RequestEntity();
        request.setId(requestId);
        request.setAttended(true); 

        when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

        AttendanceDTORequest dtoRequest = new AttendanceDTORequest(now, requestId, technicianId);

        RequestException exception = assertThrows(RequestException.class, () -> {
        attendanceService.markAsAttended(dtoRequest);
        });

        assertThat(exception.getMessage(), is(equalTo("La solicitud ya está atendida")));
        verify(requestRepository).findById(requestId);
        verify(attendanceRepository, Mockito.never()).save(Mockito.any());
    }
    

}
