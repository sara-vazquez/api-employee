package dev.sara.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.sara.exceptions.RequestNotFoundException;
import dev.sara.exceptions.TopicNotFoundException;
import dev.sara.implementations.IGenericService;
import dev.sara.topics.TopicEntity;
import dev.sara.topics.TopicRepository;

@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest> {

    private final RequestRepository repository;
    private final TopicRepository topicRepository;

    public RequestServiceImpl(RequestRepository repository, TopicRepository topicRepository) {
        this.repository = repository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<RequestDTOResponse> getEntities() {
        return repository.findAll().stream()
        .map(RequestMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public RequestDTOResponse storeEntity(RequestDTORequest requestDTORequest) {
        TopicEntity topic = topicRepository.findById(requestDTORequest.topicId())
        .orElseThrow(() -> new TopicNotFoundException("Tema no encontrado"));

        RequestEntity request = RequestMapper.toEntity(requestDTORequest, topic);
        request.setCreatedAt(LocalDateTime.now());

        RequestEntity requestStored = repository.save(request);
        return RequestMapper.toDTO(requestStored);
    }

    @Override
    public List<RequestDTOResponse> getEntitiesSortedByDate() {
        return repository.findAllByOrderByCreatedAtAsc()
                         .stream()
                         .map(RequestMapper::toDTO)
                         .toList();
    }

   @Override
    public RequestDTOResponse getEntityById(Long id) {
        return repository.findById(id)
                .map(RequestMapper::toDTO)
                .orElseThrow(() -> new RequestNotFoundException("No se ha encontrado la solicitud con id: " + id ));    
    }

    @Override
    public RequestDTOResponse updateEntity(Long id, RequestDTORequest dto) {
        RequestEntity existing = repository.findById(id)
        .orElseThrow(() -> new RequestNotFoundException("No se ha encontrado la solicitud con id: " + id));

        existing.setName(dto.name());
        existing.setDate(dto.date());
        existing.setDescription(dto.description());
        existing.setAttended(dto.attended());
        existing.setUpdatedAt(LocalDateTime.now());


        TopicEntity topic = topicRepository.findById(dto.topicId())
        .orElseThrow(() -> new TopicNotFoundException("Tema no encontrado"));
        existing.setTopic(topic);

        return RequestMapper.toDTO(repository.save(existing));

    }
    
}