package dev.sara.request;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sara.implementations.IGenericService;


@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IGenericService<RequestDTOResponse, RequestDTORequest> requestService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return all requests")
    void textIndex_ShouldReturnRequests() throws Exception{
        RequestDTOResponse request1 = new RequestDTOResponse(1L,"Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema");
        RequestDTOResponse request2 = new RequestDTOResponse(2L,"Paco", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema");
        List<RequestDTOResponse> requests = List.of(request1, request2);
        String json = mapper.writeValueAsString(requests);

        when(requestService.getEntities()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));

    }

    @Test
    void testStore_ShouldReturnStatus201() throws Exception {
        RequestDTORequest dto = new RequestDTORequest("Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema");
        RequestDTOResponse Conchi = new RequestDTOResponse(1L,"Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema");
        String json = mapper.writeValueAsString(dto);

        when(requestService.storeEntity(dto)).thenReturn(Conchi);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
         .andExpect(status().isCreated())
         .andReturn()
         .getResponse();

        assertThat(response.getContentAsString(), containsString(Conchi.name()));
    }

    @Test
    void testStoreRequest_ShouldReturnStatus400_IfNameIsEmpty() throws Exception {
        RequestDTORequest dto = new RequestDTORequest("", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema");
        String json = mapper.writeValueAsString(dto);
        when(requestService.storeEntity(dto)).thenReturn(null);
        mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testStoreCountry_ShouldReturnNoContent_IfServiceDoesNotReturnAnyValue() throws Exception {
        RequestDTORequest dto = new RequestDTORequest("Paco", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema");
        String json = mapper.writeValueAsString(dto);

        when(requestService.storeEntity(dto)).thenReturn(null);
        mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
                .andExpect(status().isNoContent());
    }

}
