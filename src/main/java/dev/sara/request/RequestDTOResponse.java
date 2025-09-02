package dev.sara.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.sara.topics.TopicEntity;

public record RequestDTOResponse( Long id, 
    String name, 
    @JsonFormat(pattern = "dd-MM-yyyy")LocalDate date, 
    TopicEntity topic, 
    String description,
    boolean attended,
    LocalDateTime createdAt
    ) {
}
