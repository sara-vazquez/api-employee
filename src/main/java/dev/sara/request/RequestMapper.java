package dev.sara.request;

public class RequestMapper {
   
    public static RequestEntity toEntity(RequestDTORequest dtoRequest) {
        RequestEntity request = new RequestEntity();
        request.setName(dtoRequest.name());
        request.setDate(dtoRequest.date());
        request.setTopic(dtoRequest.topic());
        request.setDescription(dtoRequest.description());

        return request;
    }

    public static RequestDTOResponse toDTO(RequestEntity entity) {
        RequestDTOResponse dtoResponse = new RequestDTOResponse(entity.getId(), entity.getName(), entity.getDate(), entity.getTopic(), entity.getDescription());
        return dtoResponse;
    }
}
