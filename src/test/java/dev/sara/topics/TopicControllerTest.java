package dev.sara.topics;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sara.implementations.ITopicService;

@WebMvcTest(TopicController.class)
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper; 

    @MockitoBean
    private ITopicService<TopicDTOResponse> topicService;

    @Test
    @DisplayName("Should return all topics")
    void testGetTopics_ShouldReturnAllTopics() throws Exception {
        TopicDTOResponse topic1 = new TopicDTOResponse(1L, "Fallo al cargar");
        TopicDTOResponse topic2 = new TopicDTOResponse(2L, "Error de entorno");
        TopicDTOResponse topic3 = new TopicDTOResponse(3L, "Bloqueo del sistema");
        TopicDTOResponse topic4 = new TopicDTOResponse(4L, "Error de usuario");
        List<TopicDTOResponse> topics = List.of(topic1, topic2, topic3, topic4);
        String json = mapper.writeValueAsString(topics);

        when(topicService.getAllEntities()).thenReturn(topics);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/topics"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }
}
