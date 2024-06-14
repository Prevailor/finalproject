package com.training.licenselifecycletracker.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.repositories.LifecycleEventRepository;
import com.training.licenselifecycletracker.service.LifecycleeventServiceImpl;

@SpringBootTest
public class LifecycleeventServiceImplTest {

    @Mock
    private LifecycleEventRepository repo;

    @InjectMocks
    private LifecycleeventServiceImpl service;

    private LifecycleEvent mockEvent;

    @BeforeEach
    public void setUp() {
        // Initialize a mock LifecycleEvent
        mockEvent = new LifecycleEvent();
        mockEvent.setEventId(1);
        mockEvent.setRelatedId(1);
        mockEvent.setEventType("Test");
        mockEvent.setEventDate(LocalDate.now());
        mockEvent.setDescription("Test description");
        mockEvent.setCategory("Test category");
    }

    @Test
    public void testAddLifeCycleEvent_Success() {
        // Mock the repository behavior
        when(repo.save(any())).thenReturn(mockEvent);

        // Call the method under test
        LifecycleEvent result = service.addLifeCycleEvent(mockEvent);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockEvent, result);
    }

    @Test
    public void testGetLifeCycleEventById_Success() throws LifecycleEventNotFoundException {
        // Mock the repository behavior
        when(repo.findById(1)).thenReturn(Optional.of(mockEvent));

        // Call the method under test
        LifecycleEvent result = service.getLifeCycleEventById(1);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockEvent, result);
    }

    // Write similar test cases for other methods like getLifeCycleEventByEventType, getLifeCycleEventByEventDate, etc.

    @Test
    public void testDeleteLifeCycleEventById_Success() throws LifecycleEventNotFoundException {
        // Mock the repository behavior
        when(repo.findById(1)).thenReturn(Optional.of(mockEvent));

        // Call the method under test
        String result = service.deleteLifeCycleEventById(1);

        // Verify the result
        assertNotNull(result);
        assertEquals("Lifecycle event with Id 1 deleted successfully", result);
    }

    @Test
    public void testUpdateLifeCycleEvent_Success() throws LifecycleEventNotFoundException {
        // Mock the repository behavior
        when(repo.findById(1)).thenReturn(Optional.of(mockEvent));
        when(repo.save(any())).thenReturn(mockEvent);

        // Call the method under test
        LifecycleEvent result = service.updateLifeCycleEvent(mockEvent);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockEvent, result);
    }

    @Test
    void testViewLifecycleEvent_Success() throws LifecycleEventNotFoundException {
        // Mock data
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findAll()).thenReturn(lifecycleEvents);

        // Call the service method
        ResponseEntity<List<LifecycleEvent>> result = service.viewLifecycleEvent();

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(lifecycleEvents, result.getBody());
    }

    @Test
    void testViewLifecycleEvent_NotFound1() {
        // Mock the behavior of repository to return an empty list
        when(repo.findAll()).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.viewLifecycleEvent();
        });
    }
    @Test
    public void testGetLifeCycleEventById_NotFound() {
        // Mock the repository behavior to return empty optional
        when(repo.findById(1)).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.getLifeCycleEventById(1);
        });
    }

    @Test
    void testGetLifeCycleEventByEventType_Success() throws LifecycleEventNotFoundException {
        // Mock data
        String eventType = "sampleType";
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findByEventType(eventType)).thenReturn(lifecycleEvents);

        // Call the service method
        List<LifecycleEvent> result = service.getLifeCycleEventByEventType(eventType);

        // Verify the result
        assertEquals(lifecycleEvents, result);
    }

    @Test
    void testGetLifeCycleEventByEventType_NotFound() {
        // Mock data
        String eventType = "nonexistentType";

        // Mock the behavior of repository to return an empty list
        when(repo.findByEventType(eventType)).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
        	service.getLifeCycleEventByEventType(eventType);
        });
    }
    @Test
    public void testDeleteLifeCycleEventById_NotFound() {
        // Mock the repository behavior to return empty optional
        when(repo.findById(1)).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.deleteLifeCycleEventById(1);
        });
    }

    @Test
    public void testUpdateLifeCycleEvent_NotFound() {
        // Mock the repository behavior to return empty optional
        when(repo.findById(1)).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.updateLifeCycleEvent(mockEvent);
        });
    }
    
    @Test
    void testViewLifecycleEvents_Success() throws LifecycleEventNotFoundException {
        // Given
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent(/* event details */));
        when(repo.findAll()).thenReturn(lifecycleEvents);

        // When
        ResponseEntity<List<LifecycleEvent>> response = service.viewLifecycleEvents();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lifecycleEvents, response.getBody());
    }

    @Test
    void testViewLifecycleEvents_NoEventsFound() {
        // Given
        when(repo.findAll()).thenReturn(new ArrayList<>());

        // When, Then
        assertThrows(LifecycleEventNotFoundException.class, () ->
        service.viewLifecycleEvents()
        );
    }
    @Test
    void testGenerateLifecycleReports_Success() throws LifecycleEventNotFoundException {
        // Mock data
        List<Integer> relatedIds = Arrays.asList(1, 2, 3);
        List<LifecycleEvent> eventsForAsset1 = Arrays.asList(new LifecycleEvent(), new LifecycleEvent());
        List<LifecycleEvent> eventsForAsset2 = Arrays.asList(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findAllByRelatedId(1)).thenReturn(eventsForAsset1);
        when(repo.findAllByRelatedId(2)).thenReturn(eventsForAsset2);
        when(repo.findAllByRelatedId(3)).thenReturn(new ArrayList<>());

        // Call the service method
        List<LifecycleEvent> result = service.generateLifecycleReports(relatedIds);

        // Verify the result
        assertEquals(3, result.size());
    }

    @Test
    void testGenerateLifecycleReports_NotFound() {
        // Mock data
        List<Integer> relatedIds = Arrays.asList(1, 2, 3);

        // Mock the behavior of repository to return empty lists for all related IDs
        when(repo.findAllByRelatedId(1)).thenReturn(new ArrayList<>());
        when(repo.findAllByRelatedId(2)).thenReturn(new ArrayList<>());
        when(repo.findAllByRelatedId(3)).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.generateLifecycleReports(relatedIds);
        });
    }
    
    @Test
    void testGetLifeCycleEventByEventDate_Success() throws LifecycleEventNotFoundException {
        // Mock data
        LocalDate eventDate = LocalDate.now();
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findByEventDate(eventDate)).thenReturn(lifecycleEvents);

        // Call the service method
        List<LifecycleEvent> result = service.getLifeCycleEventByEventDate(eventDate);

        // Verify the result
        assertEquals(lifecycleEvents, result);
    }

    @Test
    void testGetLifeCycleEventByEventDate_NotFound() {
        // Mock data
        LocalDate eventDate = LocalDate.now();

        // Mock the behavior of repository to return an empty list
        when(repo.findByEventDate(eventDate)).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
        	service.getLifeCycleEventByEventDate(eventDate);
        });
    }


    @Test
    void testGetLifeCycleEventByDescription_Success() throws LifecycleEventNotFoundException {
        // Mock data
        String description = "Test Description";
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findByDescription(description)).thenReturn(lifecycleEvents);

        // Call the service method
        List<LifecycleEvent> result = service.getLifeCycleEventByDescription(description);

        // Verify the result
        assertEquals(lifecycleEvents, result);
    }

    @Test
    void testGetLifeCycleEventByDescription_NotFound() {
        // Mock data
        String description = "Nonexistent Description";

        // Mock the behavior of repository to return an empty list
        when(repo.findByDescription(description)).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.getLifeCycleEventByDescription(description);
        });
    }
    
    @Test
    void testGetLifeCycleEventByCategory_Success() throws LifecycleEventNotFoundException {
        // Mock data
        String category = "TestCategory";
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        lifecycleEvents.add(new LifecycleEvent());

        // Mock the behavior of repository
        when(repo.findByCategory(category)).thenReturn(lifecycleEvents);

        // Call the service method
        List<LifecycleEvent> result = service.getLifeCycleEventByCategory(category);

        // Verify the result
        assertEquals(lifecycleEvents, result);
    }

    @Test
    void testGetLifeCycleEventByCategory_NotFound() {
        // Mock data
        String category = "NonexistentCategory";

        // Mock the behavior of repository to return an empty list
        when(repo.findByCategory(category)).thenReturn(new ArrayList<>());

        // Call the service method and verify that it throws LifecycleEventNotFoundException
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            service.getLifeCycleEventByCategory(category);
        });
    
}
}
