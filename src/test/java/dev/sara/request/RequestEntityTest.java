package dev.sara.request;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

import dev.sara.topics.TopicEntity;


public class RequestEntityTest {
    
    @Test
    void testRequestEntity_Initialization() {
        TopicEntity topic = new TopicEntity("Error de entorno");
        RequestEntity request = new RequestEntity(1L, "Sara",LocalDate.of(2025, 8, 28), topic, "No voy a llorar", false);

        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getClass().getDeclaredFields().length, is(equalTo(8)));
        assertThat(request.getTopic().getName(), is(equalTo("Error de entorno")));

    }

    @Test
    void testRequestEntity() {
        TopicEntity topic = new TopicEntity("Error de entorno");
        RequestEntity request = new RequestEntity();
        request.setId(1L);
        request.setName("Sara");
        request.setDate(LocalDate.of(2025, 8, 28));
        request.setTopic(topic);
        request.setDescription("No voy a llorar");
        request.setAttended(false);
        
        assertThat(request.getId(), is(equalTo(1L)));
        assertThat(request.getName(), is(equalTo("Sara")));
        assertThat(request.getDate(), is(equalTo(LocalDate.of(2025, 8, 28))));
        assertThat(request.getTopic().getName(), is(equalTo("Error de entorno")));
        assertThat(request.getDescription(), equalTo("No voy a llorar"));
        assertThat(request.isAttended(), equalTo(false));
    }

    @Test
    void testRequestEntity2_WithBuilder() {
        TopicEntity topic = new TopicEntity("Bloqueo del sistema");
        RequestEntity request = RequestEntity.builder()
        .id(2L)
        .name("Álvaro")
        .date(LocalDate.of(2025, 8, 28))
        .topic(topic)
        .description("Error en el sistema")
        .attended(true)
        .build();

        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getId(), is(equalTo(2L)));
        assertThat(request.getName(), is(equalTo("Álvaro")));
        assertThat(request.getDate(), is(equalTo(LocalDate.of(2025, 8, 28))));
        assertThat(request.getTopic().getName(), is(equalTo("Bloqueo del sistema")));
        assertThat(request.getDescription(), is(equalTo("Error en el sistema")));
        assertThat(request.isAttended(), equalTo(true));
    }
}
