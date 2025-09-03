package dev.sara.request;

import dev.sara.topics.TopicEntity;

public class RequestMapper {
   
    public static RequestEntity toEntity(RequestDTORequest dtoRequest, TopicEntity topic) {
        RequestEntity request = new RequestEntity();
        request.setName(dtoRequest.name());
        request.setDate(dtoRequest.date());
        request.setTopic(topic);
        request.setDescription(dtoRequest.description());
        request.setAttended(dtoRequest.attended());

        return request;
    }

    public static RequestDTOResponse toDTO(RequestEntity entity) {
        return new RequestDTOResponse(
            entity.getId(),
            entity.getName(),
            entity.getDate(),
            entity.getTopic().getId(),
            entity.getDescription(),
            entity.isAttended(),
            entity.getCreatedAt()
        );
    }    
}
