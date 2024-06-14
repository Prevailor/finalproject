package com.training.licenselifecycletracker.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.controller.ManagementController;
import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.service.LifecycleeventService;

@SpringBootTest
public class ManagementControllerTest {

    @Mock
    private LifecycleeventService lifecycleeventService;

    @InjectMocks
    private ManagementController controller;

    @Test
    public void testGenerateLifecycleReports_Success() throws LifecycleEventNotFoundException {
        // Mock the service method to return a list of lifecycle events
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        when(lifecycleeventService.generateLifecycleReports(anyList())).thenReturn(lifecycleEvents);

        // Call the controller method with some relatedIds
        List<Integer> relatedIds = List.of(1, 2, 3);
        ResponseEntity<?> response = controller.generateLifecycleReports(relatedIds);

        // Assert that the response is OK and contains the list of lifecycle events
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lifecycleEvents, response.getBody());
    }

    @Test
    public void testGenerateLifecycleReports_NotFound() throws LifecycleEventNotFoundException {
        // Mock the service method to throw LifecycleEventNotFoundException
        when(lifecycleeventService.generateLifecycleReports(anyList())).thenThrow(new LifecycleEventNotFoundException("Lifecycle events not found"));

        // Call the controller method with some relatedIds
        List<Integer> relatedIds = List.of(1, 2, 3);
        ResponseEntity<?> response = controller.generateLifecycleReports(relatedIds);

        // Assert that the response is NOT_FOUND and contains the error message
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Lifecycle events not found for provided asset IDs: [1, 2, 3]", response.getBody());
    }
    
    @Test
    public void testViewLifecycleEvents_Success() throws LifecycleEventNotFoundException {
        // Mock the service method to return a list of lifecycle events
        List<LifecycleEvent> lifecycleEvents = new ArrayList<>();
        when(lifecycleeventService.viewLifecycleEvent()).thenReturn(ResponseEntity.ok(lifecycleEvents));

        // Call the controller method
        ResponseEntity<?> response = controller.viewLifecycleEvents();

        // Assert that the response is OK and contains the list of lifecycle events
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lifecycleEvents, response.getBody());
    }

    @Test
    public void testViewLifecycleEvents_NotFound() throws LifecycleEventNotFoundException {
        // Mock the service method to throw LifecycleEventNotFoundException
        String errorMessage = "No LifecycleEvent Found";
        when(lifecycleeventService.viewLifecycleEvent()).thenThrow(new LifecycleEventNotFoundException(errorMessage));

        // Call the controller method
        ResponseEntity<?> response = controller.viewLifecycleEvents();

        // Assert that the response is NOT_FOUND and contains the error message
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
}
