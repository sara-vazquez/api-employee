package dev.sara.request;

import java.time.LocalDate;

public class RequestEntity {
    
    private String name;
    private LocalDate date;
    private String topic;
    private String description;

    public RequestEntity(String name, LocalDate date, String topic, String description ) {
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.description = description;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
