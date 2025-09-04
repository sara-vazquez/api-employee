package dev.sara.technician;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.sara.exceptions.TechnicianNotFoundException;
import dev.sara.implementations.IGenericService;

@Service
public class TechnicianServiceImpl implements IGenericService<TechnicianDTOResponse, TechnicianDTORequest>{

    private final TechnicianRepository repository;

    public TechnicianServiceImpl(TechnicianRepository repository) {
        this.repository = repository;
    }

     @Override
    public List<TechnicianDTOResponse> getEntities() {
        return repository.findAll().stream()
        .map(TechnicianMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public TechnicianDTOResponse storeEntity(TechnicianDTORequest dto) {
        TechnicianEntity technician = TechnicianMapper.toEntity(dto);
        TechnicianEntity saved = repository.save(technician);
        return TechnicianMapper.toDTO(saved);
    }

    @Override
    public TechnicianDTOResponse getEntityById(Long id) {
        TechnicianEntity technician = repository.findById(id)
            .orElseThrow(() -> new TechnicianNotFoundException("No encontrado el técnico con id: " + id));
            return TechnicianMapper.toDTO(technician);
    }

    @Override
    public TechnicianDTOResponse updateEntity(Long id, TechnicianDTORequest dto) {
        TechnicianEntity existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado con id: " + id));

        existing.setTechnicianName(dto.technicianName());
        TechnicianEntity updated = repository.save(existing);
            return TechnicianMapper.toDTO(updated);
    }


}
