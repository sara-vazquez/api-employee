package dev.sara.request;

import java.time.LocalDate;

public record RequestDTORequest(Long id, String name, LocalDate date, String topic, String description) {

}
