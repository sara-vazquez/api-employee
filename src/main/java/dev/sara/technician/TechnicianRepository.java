package dev.sara.technician;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Long> {
    
}
