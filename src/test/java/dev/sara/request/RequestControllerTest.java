package dev.sara.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sara.implementations.IGenericService;

@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IGenericService<RequestEntity> requestService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return all requests")
    void textIndex_ShouldReturnRequests() throws Exception{
        RequestEntity request1 = new RequestEntity(1L,"Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema");
        RequestEntity request2 = new RequestEntity(2L,"Paco", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema");
        List<RequestEntity> requests = List.of(request1, request2);
        String json = mapper.writeValueAsString(requests);

        when(requestService.getEntities()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));

    }

}
