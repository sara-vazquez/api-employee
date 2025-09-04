package dev.sara.attendance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dev.sara.request.RequestEntity;
import dev.sara.technician.TechnicianEntity;


public class AttendanceEntityTest {
    @Test
    void testAttendanceEntity_Initialization() {
       LocalDateTime now = LocalDateTime.now();

        RequestEntity request = new RequestEntity();
        TechnicianEntity technician = new TechnicianEntity("Dwight Schrute");

        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setAttendedAt(now);
        attendance.setRequest(request);
        attendance.setTechnician(technician);

        assertThat(attendance, is(instanceOf(AttendanceEntity.class)));
        assertThat(attendance.getClass().getDeclaredFields().length, is(equalTo(4)));
        assertThat(attendance.getAttendedAt(), is(equalTo(now)));
        assertThat(attendance.getRequest(), is(equalTo(request)));
        assertThat(attendance.getTechnician(), is(equalTo(technician)));
    }

    @Test
    void testAttendanceEntity() {
        LocalDateTime now = LocalDateTime.now();
        RequestEntity request = new RequestEntity();
        TechnicianEntity technician = new TechnicianEntity("Dwight Schrute");

        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setId(1L);
        attendance.setAttendedAt(now);
        attendance.setRequest(request);
        attendance.setTechnician(technician);

        assertThat(attendance.getId(), is(equalTo(1L)));
        assertThat(attendance.getAttendedAt(), is(equalTo(now)));
        assertThat(attendance.getRequest(), is(equalTo(request)));
        assertThat(attendance.getTechnician(), is(equalTo(technician)));
        assertThat(attendance.getTechnician().getTechnicianName(), is(equalTo("Dwight Schrute")));
    }
}
