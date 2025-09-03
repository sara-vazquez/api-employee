package dev.sara.topics;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository repository;

    @BeforeEach
    void setUp() {
        topicService = new TopicServiceImpl(repository);
    }

    @Test
    void testGetTopics_ShouldReturnAllTopics() {
        List<TopicEntity> topicMock = List.of(
            new TopicEntity(1L, "Fallo al cargar"),
            new TopicEntity(2L, "Error de entorno"),
            new TopicEntity(3L, "Bloqueo del sistema"),
            new TopicEntity(4L, "Error de usuario")
        );
        when(repository.findAll()).thenReturn(topicMock);
        List<TopicDTOResponse> topic = topicService.getAllEntities();

        assertThat(topic.size(), is(equalTo(4)));
        assertThat(topic.get(0).name(), is(equalTo("Fallo al cargar")));
        assertThat(topic.get(1).name(), is(equalTo("Error de entorno")));
        assertThat(topic.get(2).name(), is(equalTo("Bloqueo del sistema")));
        assertThat(topic.get(3).name(), is(equalTo("Error de usuario")));
    }

}
