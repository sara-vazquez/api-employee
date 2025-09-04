package dev.sara.attendance;

import java.time.LocalDateTime;

import dev.sara.request.RequestEntity;
import dev.sara.technician.TechnicianEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "attendances")
public class AttendanceEntity {
    
    @Id
    @Column(name = "id_attendance", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attended_at", nullable= false)
    private LocalDateTime attendedAt;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private RequestEntity request;

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = false, unique = true)
    private TechnicianEntity technician;

    public AttendanceEntity(LocalDateTime attendedAt, RequestEntity request, TechnicianEntity technician) {
        this.attendedAt = attendedAt;
        this.request = request;
        this.technician = technician;

    }

    public AttendanceEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAttendedAt() {
        return attendedAt;
    }

    public void setAttendedAt(LocalDateTime attendedAt) {
        this.attendedAt = attendedAt;
    }

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    public TechnicianEntity getTechnician() {
        return technician;
    }

    public void setTechnician(TechnicianEntity technician) {
        this.technician = technician;
    }

}
