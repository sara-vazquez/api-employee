package dev.sara.topics;

public class TopicMapper {
    
    public static TopicDTOResponse toDTO(TopicEntity topic) {
        return new TopicDTOResponse(topic.getId(), topic.getName());
    }

}
