package dev.sara.request;

import java.util.List;
import org.springframework.stereotype.Service;

import dev.sara.implementations.IGenericService;

@Service
public class RequestServiceImpl implements IGenericService<RequestEntity> {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequestEntity> getEntities() {
        return repository.findAll();
    }
}