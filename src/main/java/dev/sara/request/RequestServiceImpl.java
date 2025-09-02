package dev.sara.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.sara.implementations.IGenericService;

@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest> {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequestDTOResponse> getEntities() {
        return repository.findAll().stream()
        .map(RequestMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public RequestDTOResponse storeEntity(RequestDTORequest requestDTORequest) {
        RequestEntity request = RequestMapper.toEntity(requestDTORequest);
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

   /*  @Override
    public List<RequestDTOResponse> getEntitiesSortedById() {
        return repository.findAllById()
                         .stream()
                         .map(RequestMapper::toDTO)
                         .toList();
    } */
    
}