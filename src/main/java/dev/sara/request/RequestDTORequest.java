package dev.sara.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.sara.topics.TopicEntity;

public record RequestDTORequest(
    String name, 
    @JsonFormat(pattern = "dd-MM-yyyy")LocalDate date, 
    TopicEntity topic, 
    String description,
    boolean attended
    ) {

}
