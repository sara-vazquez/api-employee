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
import org.mockito.Mockito;
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
            new RequestEntity(1L,"Conchi", LocalDate.of(2025, 8, 28), "Error", "Se me cae el sistema", "Active"),
            new RequestEntity(2L,"Paco", LocalDate.of(2025, 9, 5), "Fallo", "Fallo del sistema", "Inactive"));

        when(repository.findAll()).thenReturn(requestsMock);
        List<RequestDTOResponse> request = requestService.getEntities();

        assertThat(request.size(), is(equalTo(2)));
        assertThat(request.get(0).name(), is(equalTo("Conchi")));
        assertThat(request.get(1).name(), is(equalTo("Paco")));
    }

    @Test
    void testStoreRequest_ShouldReturnRequestEntity() {
        RequestDTORequest dto = new RequestDTORequest("Julia", LocalDate.of(2025, 8, 29), "Problemas", "El sistema da problemas", "Active");
        when(repository.save(Mockito.any(RequestEntity.class))).thenReturn(new RequestEntity(3L, dto.name(),dto.date(),dto.topic(), dto.description(), dto.status()));

        RequestDTOResponse storedEntity = requestService.storeEntity(dto);

        assertThat(storedEntity.id(), is(equalTo(3L)));
        assertThat(storedEntity.name(), is(equalTo("Julia")));
        assertThat(storedEntity.date(), is(equalTo(LocalDate.of(2025, 8, 29))));
        assertThat(storedEntity.topic(), is(equalTo("Problemas")));
        assertThat(storedEntity.description(), is(equalTo("El sistema da problemas")));
        assertThat(storedEntity.status(), is(equalTo("Active")));
    }

}
