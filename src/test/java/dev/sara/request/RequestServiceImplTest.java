package dev.sara.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.sara.exceptions.RequestException;
import dev.sara.topics.TopicEntity;
import dev.sara.topics.TopicRepository;


@ExtendWith(MockitoExtension.class)
public class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private RequestRepository repository;

    @Mock
    private TopicRepository topicRepository;
    
    @Test
    void testGetRequests_ShouldReturnAllRequests() {
        TopicEntity topic = new TopicEntity(2L, "Bloqueo del sistema");
        List<RequestEntity> requestsMock = List.of(
            new RequestEntity(1L,"Conchi", LocalDate.of(2025, 8, 28), topic, "Se me cae el sistema", false),
            new RequestEntity(2L,"Paco", LocalDate.of(2025, 9, 5), topic, "Fallo del sistema", false));

        when(repository.findAll()).thenReturn(requestsMock);
        List<RequestDTOResponse> request = requestService.getEntities();

        assertThat(request.size(), is(equalTo(2)));
        assertThat(request.get(0).name(), is(equalTo("Conchi")));
        assertThat(request.get(1).name(), is(equalTo("Paco")));
    }

    @Test
    void testStoreRequest_ShouldReturnRequestEntity() {
    TopicEntity topic = new TopicEntity(2L, "Bloqueo del sistema");
    RequestDTORequest dto = new RequestDTORequest("Julia", LocalDate.of(2025, 8, 29), 2L, "El sistema da problemas", true);
    
    when(topicRepository.findById(2L)).thenReturn(Optional.of(topic));
        
    RequestEntity savedEntity = new RequestEntity(3L, "Julia", LocalDate.of(2025, 8, 29), topic, "El sistema da problemas", true);
    when(repository.save(Mockito.any(RequestEntity.class))).thenReturn(savedEntity);

    RequestDTOResponse storedEntity = requestService.storeEntity(dto);

    assertThat(storedEntity.id(), is(equalTo(3L)));
    assertThat(storedEntity.name(), is(equalTo("Julia")));
    assertThat(storedEntity.date(), is(equalTo(LocalDate.of(2025, 8, 29))));
    assertThat(storedEntity.topicId(), is(equalTo(2L)));
    assertThat(storedEntity.description(), is(equalTo("El sistema da problemas")));
    assertThat(storedEntity.attended(), is(equalTo(true)));
    }

    @Test
    void testUpdateEntity_ShouldUpdateRequest() {
    Long requestId = 1L;

    RequestDTORequest dto = new RequestDTORequest(
        "Julia González",
        LocalDate.of(2025, 9, 10),
        2L,
        "Congelado",
        true
    );

    TopicEntity topic = new TopicEntity();
    topic.setId(2L);
    topic.setName("Bloqueo del sistema");

    RequestEntity existing = new RequestEntity();
    existing.setId(requestId);
    existing.setName("Julia");
    existing.setDate(LocalDate.of(2025, 9, 1));
    existing.setDescription("El sistema da problemas");
    existing.setAttended(false);
    existing.setCreatedAt(LocalDateTime.of(2025, 8, 28, 12, 0));
    existing.setUpdatedAt(null);

    when(repository.findById(requestId)).thenReturn(Optional.of(existing));
    when(topicRepository.findById(dto.topicId())).thenReturn(Optional.of(topic));
    when(repository.save(any(RequestEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

    RequestDTOResponse result = requestService.updateEntity(requestId, dto);

    assertThat(result.id()).isEqualTo(requestId);
    assertThat(result.name()).isEqualTo("Julia González");
    assertThat(result.description()).isEqualTo("Congelado");
    assertThat(result.attended()).isTrue();
    assertThat(result.topicId()).isEqualTo(2L);
    assertThat(result.updatedAt()).isNotNull();

    verify(repository).findById(requestId);
    verify(topicRepository).findById(2L);
    verify(repository).save(existing);
}

@Test
void testDeleteRequest_Attended_ShouldDelete() {
    RequestEntity request = new RequestEntity();
    request.setId(1L);
    request.setAttended(true);

    when(repository.findById(1L)).thenReturn(Optional.of(request));

    requestService.deleteRequest(1L);

    verify(repository).delete(request);
}

@Test
void testDeleteRequest_Pending_ShouldThrowException() {
    RequestEntity request = new RequestEntity();
    request.setId(2L);
    request.setAttended(false);

    when(repository.findById(2L)).thenReturn(Optional.of(request));

    assertThrows(RequestException.class, () -> requestService.deleteRequest(2L));
}



    

}
