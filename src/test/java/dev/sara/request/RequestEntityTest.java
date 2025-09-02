package dev.sara.request;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;


public class RequestEntityTest {
    
    @Test
    void testRequestEntity_Initialization() {
        RequestEntity request = new RequestEntity(1L, "Sara",LocalDate.of(2025, 8, 28), "Spring", "No voy a llorar", "Pendiente");

        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getClass().getDeclaredFields().length, is(equalTo(6)));
    }

    @Test
    void testRequestEntity() {
        RequestEntity request = new RequestEntity();
        request.setId(1L);
        request.setName("Sara");
        request.setDate(LocalDate.of(2025, 8, 28));
        request.setTopic("Spring");
        request.setDescription("No voy a llorar");
        request.setStatus("Pendiente");
        
        assertThat(request.getId(), is(equalTo(1L)));
        assertThat(request.getName(), is(equalTo("Sara")));
        assertThat(request.getDate(), is(equalTo(LocalDate.of(2025, 8, 28))));
        assertThat(request.getTopic(), is(equalTo("Spring")));
        assertThat(request.getDescription(), equalTo("No voy a llorar"));
        assertThat(request.getStatus(), equalTo("Pendiente"));
    }

    @Test
    void testRequestEntity2_WithBuilder() {
        RequestEntity request = RequestEntity.builder()
        .id(2L)
        .name("Álvaro")
        .date(LocalDate.of(2025, 8, 28))
        .topic("Error")
        .description("Error en el sistema")
        .status("Atendida")
        .build();

        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getId(), is(equalTo(2L)));
        assertThat(request.getName(), is(equalTo("Álvaro")));
        assertThat(request.getDate(), is(equalTo(LocalDate.of(2025, 8, 28))));
        assertThat(request.getTopic(), is(equalTo("Error")));
        assertThat(request.getDescription(), is(equalTo("Error en el sistema")));
        assertThat(request.getStatus(), equalTo("Atendida"));

    }
}
