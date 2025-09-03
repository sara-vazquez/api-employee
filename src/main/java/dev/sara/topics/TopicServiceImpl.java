package dev.sara.topics;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.sara.exceptions.TopicNotFoundException;
import dev.sara.implementations.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService<TopicEntity> {

    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TopicEntity> getAllEntities() {
        return repository.findAll();
    }

    @Override
    public TopicEntity findById(Long id) {
        return repository.findById(id)
                        .orElseThrow(() -> new TopicNotFoundException("Tema no encontrado con id " + id));
    }
    
}
