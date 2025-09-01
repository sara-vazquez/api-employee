package dev.sara.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        List<RequestDTOResponse> requests = new ArrayList<>();

        repository.findAll().forEach(r -> {
            RequestDTOResponse request = RequestMapper.toDTO(r);
            requests.add(request);
        });

        return requests;
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
}