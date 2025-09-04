package dev.sara.request;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sara.implementations.ISortableService;

@RestController
@RequestMapping(path = "${api-endpoint}/requests")
public class RequestController {
    private final ISortableService<RequestDTOResponse, RequestDTORequest> service;
    private final RequestServiceImpl requestService;

    public RequestController(ISortableService<RequestDTOResponse, RequestDTORequest> service, RequestServiceImpl requestService) {
        this.service = service;
        this.requestService = requestService;
    }
      
    @GetMapping("")
    public List<RequestDTOResponse> getEntities() {
        return service.getEntities();
    }

    @PostMapping("")
    public ResponseEntity<RequestDTOResponse> storeEntity(@RequestBody RequestDTORequest dtoRequest) {
        if (dtoRequest.name().isBlank()) return ResponseEntity.badRequest().build();
        RequestDTOResponse entityStored = service.storeEntity(dtoRequest);

       if (entityStored == null) return ResponseEntity.noContent().build();
        return ResponseEntity.status(201).body(entityStored);
    }

    @GetMapping("/date")
    public List<RequestDTOResponse> getRequestsSortedByDate() {
        return service.getEntitiesSortedByDate();
    }

   @GetMapping("/{id}")
    public ResponseEntity<RequestDTOResponse> getEntityById(@PathVariable Long id) {
        RequestDTOResponse response = service.getEntityById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDTOResponse> updatRequest(@PathVariable Long id, @RequestBody RequestDTORequest dtoRequest) {
        if (dtoRequest.name() == null || dtoRequest.name().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
       
        RequestDTOResponse updatedRequest = requestService.updateEntity(id, dtoRequest);
        return ResponseEntity.ok(updatedRequest);
    }

}
