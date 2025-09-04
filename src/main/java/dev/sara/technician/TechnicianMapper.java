package dev.sara.technician;

public class TechnicianMapper {
    
    public static TechnicianEntity toEntity(TechnicianDTORequest dtoRequest) {
        TechnicianEntity technician = new TechnicianEntity();
        technician.setTechnicianName(dtoRequest.technicianName());

        return technician;
    }

    public static TechnicianDTOResponse toDTO(TechnicianEntity entity) {
        return new TechnicianDTOResponse(
        entity.getId(),
        entity.getTechnicianName()
        );
    }
        


}
