package dev.sara.technician;

import dev.sara.attendance.AttendanceEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "technicians")
public class TechnicianEntity {
    
    @Id
    @Column(name = "id_technicians", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String technicianName;

    @OneToOne(mappedBy="technician", cascade = CascadeType.ALL)
    private AttendanceEntity attendance;


    public TechnicianEntity(Long id, String technicianName) {
        this.id = id;
        this.technicianName = technicianName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public AttendanceEntity getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceEntity attendance) {
        this.attendance = attendance;
    }

    public TechnicianEntity() {}
}
