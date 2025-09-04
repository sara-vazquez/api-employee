package dev.sara.technicians;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sara.technician.TechnicianController;
import dev.sara.technician.TechnicianDTORequest;
import dev.sara.technician.TechnicianDTOResponse;
import dev.sara.technician.TechnicianServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@WebMvcTest(controllers = TechnicianController.class)
@ExtendWith(SpringExtension.class)
public class TechnicianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TechnicianServiceImpl technicianService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testGetAllTechnicians() throws Exception {
        List<TechnicianDTOResponse> technicians = List.of(
            new TechnicianDTOResponse(1L, "Michael Scott"),
            new TechnicianDTOResponse(2L, "Dwight Schrute")
        );
        String json = mapper.writeValueAsString(technicians);

        when(technicianService.getEntities()).thenReturn(technicians);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/technicians"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test
    void testStoreTechnician_ShouldReturn201() throws Exception {
        TechnicianDTORequest dto = new TechnicianDTORequest("Jim Halpert");
        TechnicianDTOResponse dtoResponse = new TechnicianDTOResponse(3L, "Jim Halpert");
        String json = mapper.writeValueAsString(dto);

        when(technicianService.storeEntity(any(TechnicianDTORequest.class))).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/technicians")
                        .content(json)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), is(equalTo(mapper.writeValueAsString(dtoResponse))));
    }
    
}
