package dev.sara.request;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sara.implementations.IGenericService;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
public class RequestController {
    private final IGenericService<RequestEntity> service;

    public RequestController(IGenericService<RequestEntity> service) {
        this.service = service;
    }
      
    @GetMapping
    public List<RequestEntity> index() {

        return service.getEntities();
    }
}
