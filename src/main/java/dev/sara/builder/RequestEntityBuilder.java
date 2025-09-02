package dev.sara.builder;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dev.sara.request.RequestEntity;

@Component
public class RequestEntityBuilder implements IRequestBuilder {
    
    private final RequestEntity request;

    public RequestEntityBuilder() {
        this.request = new RequestEntity();
    }

    @Override
    public RequestEntityBuilder id(Long id) {
        request.setId(id);
        return this; 
    }

    @Override
    public RequestEntityBuilder name(String name) {
        request.setName(name);
        return this; 
    }

    @Override
    public RequestEntityBuilder date(LocalDate date) {
        request.setDate(date);
        return this; 
    }

    @Override
    public RequestEntityBuilder topic(String topic) {
        request.setTopic(topic);
        return this; 
    }

    @Override
    public RequestEntityBuilder description(String description) {
        request.setDescription(description);
        return this; 
    }

    @Override
    public RequestEntityBuilder attended(boolean attended) {
        request.setAttended(attended);
        return this; 
    }

    @Override
    public RequestEntity build() {
        return this.request;
    }
}
