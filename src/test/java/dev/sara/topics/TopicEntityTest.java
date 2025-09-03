package dev.sara.topics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

public class TopicEntityTest {
    
    @Test
    void testTopicEntity_Initialization() {
        TopicEntity topic = new TopicEntity(3L, "Error de usuario");

        assertThat(topic, is(instanceOf(TopicEntity.class)));
        assertThat(topic.getClass().getDeclaredFields().length, is(equalTo(2)));
        assertThat(topic.getName(), is(equalTo("Error de usuario")));
    }
}
