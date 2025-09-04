package dev.sara.attendance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AttendanceController.class)
@ExtendWith(SpringExtension.class)
public class AttendanceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AttendanceServiceImpl attendanceService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testMarkAsAttended_ShouldReturn201() throws Exception {
        AttendanceDTORequest dtoRequest = new AttendanceDTORequest(LocalDateTime.now(), 1L, 1L);
        AttendanceDTOResponse dtoResponse = new AttendanceDTOResponse(10L, dtoRequest.attendedAt(), 1L, 1L);
        String json = mapper.writeValueAsString(dtoRequest);

        when(attendanceService.markAsAttended(any(AttendanceDTORequest.class))).thenReturn(dtoResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/attendances")
                .content(json)
                .contentType("application/json"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), is(equalTo(mapper.writeValueAsString(dtoResponse))));
    }

}
