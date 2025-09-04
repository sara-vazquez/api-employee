package dev.sara.technicians;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.sara.technician.TechnicianDTORequest;
import dev.sara.technician.TechnicianDTOResponse;
import dev.sara.technician.TechnicianEntity;
import dev.sara.technician.TechnicianRepository;
import dev.sara.technician.TechnicianServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TechnicianServiceImplTest {
    
    @InjectMocks
    private TechnicianServiceImpl technicianService;

    @Mock
    private TechnicianRepository repository;

    @BeforeEach
    void setUp() {
        technicianService = new TechnicianServiceImpl(repository);
    }

    @Test
    void testGetTechnicians_ShouldReturnAllTechnicians() {
        List<TechnicianEntity> techniciansMock = List.of(
            new TechnicianEntity(1L, "Michael Scott"),
            new TechnicianEntity(2L, "Dwight Schrute")
        );

        when(repository.findAll()).thenReturn(techniciansMock);

        List<TechnicianDTOResponse> result = technicianService.getEntities();

        assertThat(result.size(), is(equalTo(2)));
        assertThat(result.get(0).technicianName(), is(equalTo("Michael Scott")));
        assertThat(result.get(1).technicianName(), is(equalTo("Dwight Schrute")));
    }

    @Test
    void testStoreTechnician_ShouldReturnTechnicianDTO() {
        TechnicianDTORequest dto = new TechnicianDTORequest("Jim Halpert");
        TechnicianEntity savedEntity = new TechnicianEntity(3L, "Jim Halpert");

        when(repository.save(Mockito.any(TechnicianEntity.class))).thenReturn(savedEntity);

        TechnicianDTOResponse result = technicianService.storeEntity(dto);

        assertThat(result.id(), is(equalTo(3L)));
        assertThat(result.technicianName(), is(equalTo("Jim Halpert")));
    }

    @Test
    void testGetTechnicianById_ShouldReturnTechnicianDTO() {
        TechnicianEntity entity = new TechnicianEntity(4L, "Pam Beesly");

        when(repository.findById(4L)).thenReturn(Optional.of(entity));

        TechnicianDTOResponse result = technicianService.getEntityById(4L);

        assertThat(result.id(), is(equalTo(4L)));
        assertThat(result.technicianName(), is(equalTo("Pam Beesly")));
    }

    @Test
    void testUpdateTechnician_ShouldUpdateAndReturnDTO() {
        Long technicianId = 4L;
        TechnicianDTORequest dto = new TechnicianDTORequest("Pam Halpert");

        TechnicianEntity existing = new TechnicianEntity(4L, "Pam Beesly");

        when(repository.findById(technicianId)).thenReturn(Optional.of(existing));
        when(repository.save(Mockito.any(TechnicianEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TechnicianDTOResponse result = technicianService.updateEntity(technicianId, dto);

        assertThat(result.id(), is(equalTo(technicianId)));
        assertThat(result.technicianName(), is(equalTo("Pam Halpert")));

        verify(repository).findById(technicianId);
        verify(repository).save(existing);
    }

}
