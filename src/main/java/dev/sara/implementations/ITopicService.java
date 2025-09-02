package dev.sara.implementations;

import java.util.List;

public interface ITopicService <T>{
    public List<T> getAllEntities();
    public T findById(Long id);
}
