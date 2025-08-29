package dev.sara.request;

import java.time.LocalDate;

import dev.sara.builder.RequestEntityBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="requests")
public class RequestEntity {

    public RequestEntity() {
    }
    
    @Id //define el campo 'id' como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera el id automáticamente
    private Long id;
    private String name;
    private LocalDate date;
    private String topic;
    private String description;

    public RequestEntity(Long id, String name, LocalDate date, String topic, String description ) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //nos devuelve el builder a través de un método estático
    public static RequestEntityBuilder builder() {
        return new RequestEntityBuilder();
    }
}
