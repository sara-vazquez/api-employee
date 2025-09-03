package dev.sara.topics;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.sara.exceptions.TopicNotFoundException;
import dev.sara.implementations.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService<TopicDTOResponse> {

    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TopicDTOResponse> getAllEntities() {
        return repository.findAll()
                         .stream()
                         .map(TopicMapper::toDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public TopicDTOResponse findById(Long id) {
        return repository.findById(id)
                        .map(TopicMapper::toDTO)
                        .orElseThrow(() -> new TopicNotFoundException("Tema no encontrado con id " + id));
    }
    
}
