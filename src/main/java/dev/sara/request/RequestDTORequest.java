package dev.sara.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record RequestDTORequest(
    String name, 
    @JsonFormat(pattern = "dd-MM-yyyy")LocalDate date, 
    Long topicId, 
    String description,
    boolean attended
    ) {

}
