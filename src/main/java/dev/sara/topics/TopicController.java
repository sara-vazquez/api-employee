package dev.sara.topics;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sara.implementations.ITopicService;

@RestController
@RequestMapping(path = "${api-endpoint}/topics")
public class TopicController {
    private final ITopicService<TopicDTOResponse> service;
    
    public TopicController(ITopicService<TopicDTOResponse> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<TopicDTOResponse> getAllTopics() {
        return service.getAllEntities();
    }

    @GetMapping("/{id}")
        public TopicDTOResponse getTopicById(@PathVariable Long id) {
            return service.findById(id);
        }
}
