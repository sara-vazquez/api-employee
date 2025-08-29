package dev.sara.request;

import java.time.LocalDate;
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
public class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private RequestRepository repository;

    @BeforeEach
    void setUp() {
        requestService = new RequestServiceImpl(repository);
    }
    
    @Test
    void testGetRequests_ShouldReturnAllRequests() {

        List<RequestEntity> requestsMock = List.of(
            new RequestEntity(1L,"Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema"),
            new RequestEntity(2L,"Paco", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema"));

        when(repository.findAll()).thenReturn(requestsMock);
        List<RequestEntity> request = requestService.getEntities();

        assertThat(request.size(), is(equalTo(2)));
        assertThat(request.get(0).getName(), is(equalTo("Conchi")));
        assertThat(request.get(1).getName(), is(equalTo("Paco")));
    }
}
