package dev.sara.builder;

import java.time.LocalDate;

import dev.sara.request.RequestEntity;

public interface IRequestBuilder {
    public RequestEntityBuilder id(Long id);
    public RequestEntityBuilder name(String name);
    public RequestEntityBuilder date(LocalDate date);
    public RequestEntityBuilder topic(String topic);
    public RequestEntityBuilder description(String description);
    public RequestEntityBuilder attended(boolean attended);

    public RequestEntity build();
}
