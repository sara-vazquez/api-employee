package dev.sara.request;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
      
    @GetMapping(path = "${api-endpoint}/requests")
    public RequestEntity index() {

        RequestEntity request1 = new RequestEntity(1L, "sara", LocalDate.of(2025, 8, 27), "spring", "ya me da problemas :(");

        return request1;
    }
}
