package dev.sara.builder;

import java.time.LocalDate;

import dev.sara.request.RequestEntity;
import dev.sara.topics.TopicEntity;

public interface IRequestBuilder {
    public RequestEntityBuilder id(Long id);
    public RequestEntityBuilder name(String name);
    public RequestEntityBuilder date(LocalDate date);
    public RequestEntityBuilder topic(TopicEntity topic);
    public RequestEntityBuilder description(String description);
    public RequestEntityBuilder attended(boolean attended);

    public RequestEntity build();
}
