package dev.sara.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.sara.builder.RequestEntityBuilder;
import dev.sara.topics.TopicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name ="requests")
public class RequestEntity {

    public RequestEntity() {
    }
    
    @Id //define el campo 'id' como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera el id automáticamente
    @Column(name="id_request", nullable = false, unique= true)
    private Long id;
    private String name;
    private LocalDate date;
    private TopicEntity topic;
    private String description;
    private boolean attended;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public RequestEntity(Long id, String name, LocalDate date, TopicEntity topic, String description, boolean attended ) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.description = description;
        this.attended = attended;
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

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //nos devuelve el builder a través de un método estático
    public static RequestEntityBuilder builder() {
        return new RequestEntityBuilder();
    }

    
}
