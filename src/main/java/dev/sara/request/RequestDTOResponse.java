package dev.sara.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record RequestDTOResponse( Long id, 
    String name, 
    @JsonFormat(pattern = "dd-MM-yyyy")LocalDate date, 
    String topic, 
    String description,
    boolean attended,
    LocalDateTime createdAt
    ) {
}
