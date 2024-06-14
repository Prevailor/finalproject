package com.training.licenselifecycletracker.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.controller.TechnicalSupportController;
import com.training.licenselifecycletracker.dto.LogFaultRequestDTO;
import com.training.licenselifecycletracker.dto.UpdateFaultLogRequestDTO;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.service.TechnicalSupportService;

@SpringBootTest
public class TechnicalSupportControllerTest {

    @Mock
    private TechnicalSupportService technicalSupportService;

    @InjectMocks
    private TechnicalSupportController controller;

    @Test
    public void testLogFault_Success() throws LifecycleEventNotFoundException {
        // Prepare request DTO
        LogFaultRequestDTO requestDTO = new LogFaultRequestDTO();
        requestDTO.setDeviceId(1);
        requestDTO.setDescription("Description");
        requestDTO.setDate("2024-06-15");
        requestDTO.setCategory("Category");

        // Mock service method to indicate successful fault logging
        doNothing().when(technicalSupportService).logFault(1, "Description", "2024-06-15", "Category");

        // Call controller method
        ResponseEntity<String> response = controller.logFault(requestDTO);

        // Assert that the response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fault logged successfully.", response.getBody());
    }

    @Test
    public void testLogFault_Error() throws LifecycleEventNotFoundException {
        // Prepare request DTO
        LogFaultRequestDTO requestDTO = new LogFaultRequestDTO();
        requestDTO.setDeviceId(1);
        requestDTO.setDescription("Description");
        requestDTO.setDate("2024-06-15");
        requestDTO.setCategory("Category");

        // Mock service method to indicate an error occurred
        doThrow(new RuntimeException("Error")).when(technicalSupportService).logFault(1, "Description", "2024-06-15", "Category");

        // Call controller method
        ResponseEntity<String> response = controller.logFault(requestDTO);

        // Assert that the response indicates an internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Error logging fault"));
    }

    @Test
    public void testUpdateFaultLog_Success() {
        // Prepare request DTO
        UpdateFaultLogRequestDTO requestDTO = new UpdateFaultLogRequestDTO();
        requestDTO.setDeviceId(1);
        requestDTO.setRepairDetails("Repair details");
        requestDTO.setCategory("Category");
        requestDTO.setEventType("EventType");

        // Mock service method to indicate successful fault log update
        doNothing().when(technicalSupportService).updateFaultLog(1, "Repair details", "Category", "EventType");

        // Call controller method
        ResponseEntity<String> response = controller.updateFaultLog(requestDTO);

        // Assert that the response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fault log updated successfully.", response.getBody());
    }

    @Test
    public void testUpdateFaultLog_Error() {
        // Prepare request DTO
        UpdateFaultLogRequestDTO requestDTO = new UpdateFaultLogRequestDTO();
        requestDTO.setDeviceId(1);
        requestDTO.setRepairDetails("Repair details");
        requestDTO.setCategory("Category");
        requestDTO.setEventType("EventType");

        // Mock service method to indicate an error occurred
        doThrow(new RuntimeException("Error")).when(technicalSupportService).updateFaultLog(1, "Repair details", "Category", "EventType");

        // Call controller method
        ResponseEntity<String> response = controller.updateFaultLog(requestDTO);

        // Assert that the response indicates an internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Error updating fault log"));
    }

    @Test
    public void testViewEndOfSupportDates_Success() {
        // Prepare mock support dates
        List<String> supportDates = new ArrayList<>();
        supportDates.add("2024-06-15");
        supportDates.add("2024-06-16");

        // Mock service method to return support dates
        when(technicalSupportService.viewEndOfSupportDates()).thenReturn(supportDates);

        // Call controller method
        ResponseEntity<List<String>> response = controller.viewEndOfSupportDates();

        // Assert that the response is OK and contains support dates
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(supportDates, response.getBody());
    }

    @Test
    public void testViewEndOfSupportDates_Error() {
        // Mock service method to indicate an error occurred
        doThrow(new RuntimeException("Error")).when(technicalSupportService).viewEndOfSupportDates();

        // Call controller method
        ResponseEntity<List<String>> response = controller.viewEndOfSupportDates();

        // Assert that the response indicates an internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
