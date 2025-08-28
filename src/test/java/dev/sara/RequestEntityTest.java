package dev.sara;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dev.sara.request.RequestEntity;

public class RequestEntityTest {
    
    @Test
    void testRequestEntity_Initialization() {
        RequestEntity request = new RequestEntity(1L, "Sara",LocalDate.of(2025, 8, 28), "Spring", "No voy a llorar");

        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getClass().getDeclaredFields().length, is(equalTo(5)));
    }
}
