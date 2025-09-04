package dev.sara.technicians;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import dev.sara.technician.TechnicianEntity;


public class TechnicianEntityTest {
    
    @Test
    void testTechnicianEntity_Initialization() {
        TechnicianEntity technician = new TechnicianEntity("Michael Scott");

        assertThat(technician, is(instanceOf(TechnicianEntity.class)));
        assertThat(technician.getClass().getDeclaredFields().length, is(equalTo(3)));
        assertThat(technician.getTechnicianName(), is(equalTo("Michael Scott")));
    }

   @Test
    void testTechnicianEntity() {
        TechnicianEntity topic = new TechnicianEntity();

        topic.setId(1L);
        topic.setTechnicianName("Michael Scott");

        assertThat(topic.getId(), is(equalTo(1L)));
        assertThat(topic.getTechnicianName(), is(equalTo("Michael Scott")));
    } 
}
