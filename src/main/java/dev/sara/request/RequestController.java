package dev.sara.request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
      
    @GetMapping("requests")
    public RequestEntity index() {

        RequestEntity request = new RequestEntity("sara", "27/08/2025 falta el formatter", "spring", "acabo de empezar y ya está fallando :(");

        // class -> json = serializar . json -> class = deserializar
        return request;
    }
}
