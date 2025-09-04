package dev.sara.technician;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sara.implementations.IGenericService;

@RestController
@RequestMapping(path = "${api-endpoint}/technicians")
public class TechnicianController {
    private final IGenericService<TechnicianDTOResponse, TechnicianDTORequest> service;
    
    public TechnicianController(IGenericService<TechnicianDTOResponse, TechnicianDTORequest> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<TechnicianDTOResponse> getEntities() {
        return service.getEntities();
    }

    @PostMapping("")
    public ResponseEntity<TechnicianDTOResponse> storeEntity(@RequestBody TechnicianDTORequest dtoRequest) {
        if (dtoRequest.technicianName().isBlank()) return ResponseEntity.badRequest().build();
        TechnicianDTOResponse entityStored = service.storeEntity(dtoRequest);

        if (entityStored == null) return ResponseEntity.noContent().build();
        return ResponseEntity.status(201).body(entityStored);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTOResponse> getEntityById(@PathVariable Long id) {
        TechnicianDTOResponse response = service.getEntityById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTOResponse> updateEntity(@PathVariable Long id, @RequestBody TechnicianDTORequest dtoRequest) {
        if (dtoRequest.technicianName() == null || dtoRequest.technicianName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        
        TechnicianDTOResponse updated = service.updateEntity(id, dtoRequest);
        return ResponseEntity.ok(updated);
    }
}
