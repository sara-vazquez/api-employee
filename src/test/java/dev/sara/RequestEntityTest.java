package dev.sara;

import org.junit.jupiter.api.Test;

import dev.sara.request.RequestEntity;

public class RequestEntityTest {
    
    @Test
    void testRequestEntity_InitializationWithIdAndName() {
        RequestEntity request = new RequestEntity(1L, "Sara", 2025-08-27, "Spring", "No voy a llorar");
    }
}
