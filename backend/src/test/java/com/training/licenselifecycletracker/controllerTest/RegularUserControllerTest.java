package com.training.licenselifecycletracker.controllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.controller.RegularUserController;
import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.entities.Software;
import com.training.licenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.licenselifecycletracker.service.DeviceService;
import com.training.licenselifecycletracker.service.LifecycleeventService;
import com.training.licenselifecycletracker.service.SoftwareService;
import com.training.licenselifecycletracker.service.UserService;

@ExtendWith(MockitoExtension.class)
public class RegularUserControllerTest {

    @Mock
    private DeviceService deviceService;

    @Mock
    private SoftwareService softwareService;

    @Mock
    private LifecycleeventService lifecycleeventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegularUserController regularUserController;

    // Test methods for Device-related endpoints

    @Test
    public void testGetDeviceByName_Success() throws DeviceNotFoundException {
        // Mock data
        String deviceName = "SampleDevice";
        List<Device> devices = Arrays.asList(new Device(), new Device());

        // Mock service method behavior
        when(deviceService.getDeviceByName(deviceName)).thenReturn(devices);

        // Perform the test
        ResponseEntity<?> response = regularUserController.getDeviceByName(deviceName);

        // Assert response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    // Add more test methods for other controller methods

    // Test methods for Lifecycle Event-related endpoints

    
    @Test
    public void testGetLifeCycleEventByEventType_NotFound() throws LifecycleEventNotFoundException {
        // Mock data
        String eventType = "NonExistentEventType";

        // Mock service method behavior
        when(lifecycleeventService.getLifeCycleEventByEventType(eventType)).thenThrow(new LifecycleEventNotFoundException("Event type not found"));

        // Perform the test
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventType(eventType);

        // Assert response status code and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Event type not found", response.getBody());
    }

    @Test
    public void testGetSoftwareByStatus_Success() throws SoftwareNotFoundException {
        // Mock data
        String status = "Active";
        List<Software> softwareList = new ArrayList<>();
        // Add some software to the list
        softwareList.add(new Software());
        softwareList.add(new Software());

        // Mock service method behavior
        when(softwareService.getSoftwareByStatus(status)).thenReturn(softwareList);

        // Perform the test
        ResponseEntity<?> response = regularUserController.getSoftwareByStatus(status);

        // Assert response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwareList, response.getBody());
    }

    @Test
    public void testGetSoftwareByStatus_NoSoftwareFound() throws SoftwareNotFoundException {
        // Mock data
        String status = "Inactive";

        // Mock service method behavior
        when(softwareService.getSoftwareByStatus(status)).thenReturn(new ArrayList<>());

        // Perform the test
        ResponseEntity<?> response = regularUserController.getSoftwareByStatus(status);

        // Assert response status code and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("No software found with status: " + status));
    }

    @Test
    public void testGetSoftwareByStatus_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock data
        String status = "Active";

        // Mock service method behavior to throw SoftwareNotFoundException
        when(softwareService.getSoftwareByStatus(status)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Perform the test
        ResponseEntity<?> response = regularUserController.getSoftwareByStatus(status);

        // Assert response status code and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    @Test
    public void testGetDeviceById_Success() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 1;
        Device device = new Device();

        // Mock service method behavior
        when(deviceService.getDeviceById(deviceId)).thenReturn(device);

        // Perform the test
        ResponseEntity<?> response = regularUserController.getDevice(deviceId);

        // Assert response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    public void testGetDeviceById_NotFound() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 999;

        // Mock service method behavior
        when(deviceService.getDeviceById(deviceId)).thenThrow(new DeviceNotFoundException("Device not found"));

        // Perform the test
        ResponseEntity<?> response = regularUserController.getDevice(deviceId);

        // Assert response status code and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    // Add more test methods for other controller methods

    // Test methods for Lifecycle Event-related endpoints

    @Test
    void testGetLifeCycleEventByEventDate_EventsFound() throws LifecycleEventNotFoundException {
        // Given
        LocalDate eventDate = LocalDate.now();
        List<LifecycleEvent> eventList = new ArrayList<>();
        eventList.add(new LifecycleEvent(/* event details */));
        when(lifecycleeventService.getLifeCycleEventByEventDate(eventDate)).thenReturn(eventList);

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventDate(eventDate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventList, response.getBody());
    }

    @Test
    void testGetLifeCycleEventByEventDate_EventsNotFound() throws LifecycleEventNotFoundException {
        // Given
        LocalDate eventDate = LocalDate.now();
        when(lifecycleeventService.getLifeCycleEventByEventDate(eventDate)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventDate(eventDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetLifeCycleEventByEventDate_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
        // Given
        LocalDate eventDate = LocalDate.now();
        when(lifecycleeventService.getLifeCycleEventByEventDate(eventDate)).thenThrow(new LifecycleEventNotFoundException("Lifecycle event not found"));

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventDate(eventDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Lifecycle event not found", response.getBody());
    }
    @Test
    public void testViewDevicesSuccess() throws DeviceNotFoundException {
        // Creating sample data
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());

        // Mocking the behavior of DeviceService
        when(deviceService.viewDevices()).thenReturn(new ResponseEntity<>(devices, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.viewDevices();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testViewDevicesDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        when(deviceService.viewDevices()).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.viewDevices();

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testGetDevicesByStatusSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());
        when(deviceService.getDeviceByStatus("Active")).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByStatus("Active");

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDevicesByStatusDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        when(deviceService.getDeviceByStatus("Inactive")).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByStatus("Inactive");

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testGetDevicesByDeviceTypeSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());
        when(deviceService.getDeviceByDeviceType("Laptop")).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByDeviceType("Laptop");

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDevicesByDeviceTypeDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        when(deviceService.getDeviceByDeviceType("Tablet")).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByDeviceType("Tablet");

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    @Test
    public void testGetDevicesByPurchaseDateSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate purchaseDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());
        when(deviceService.getDeviceByPurchaseDate(purchaseDate)).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByPurchaseDate(purchaseDate);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDevicesByPurchaseDateDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate purchaseDate = LocalDate.now();
        when(deviceService.getDeviceByPurchaseDate(purchaseDate)).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByPurchaseDate(purchaseDate);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testGetDevicesByExpirationDateSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate expirationDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());
        when(deviceService.getDeviceByExpirationDate(expirationDate)).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByExpirationDate(expirationDate);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDevicesByExpirationDateDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate expirationDate = LocalDate.now();
        when(deviceService.getDeviceByExpirationDate(expirationDate)).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByExpirationDate(expirationDate);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testGetDevicesByEndOfSupportDateSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate endOfSupportDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());
        when(deviceService.getDeviceByEndOfSupportDate(endOfSupportDate)).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByEndOfSupportDate(endOfSupportDate);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDevicesByEndOfSupportDateDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        LocalDate endOfSupportDate = LocalDate.now();
        when(deviceService.getDeviceByEndOfSupportDate(endOfSupportDate)).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDevicesByEndOfSupportDate(endOfSupportDate);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testGetDeviceByNameSuccess() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        String deviceName = "DeviceName";
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        when(deviceService.getDeviceByName(deviceName)).thenReturn(devices);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDeviceByName(deviceName);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(devices, responseEntity.getBody());
    }

    @Test
    public void testGetDeviceByNameDeviceNotFoundException() throws DeviceNotFoundException {
        // Mocking the behavior of DeviceService
        String deviceName = "NonExistingDevice";
        when(deviceService.getDeviceByName(deviceName)).thenThrow(new DeviceNotFoundException("No devices found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getDeviceByName(deviceName);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No devices found", responseEntity.getBody());
    }
    
    @Test
    public void testViewSoftwaresSuccess() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        softwares.add(new Software());
        when(softwareService.viewSoftwares()).thenReturn(new ResponseEntity<>(softwares, HttpStatus.OK));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.viewSoftwares();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(softwares, responseEntity.getBody());
    }

    @Test
    public void testViewSoftwaresSoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        when(softwareService.viewSoftwares()).thenThrow(new SoftwareNotFoundException("No softwares found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.viewSoftwares();

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No softwares found", responseEntity.getBody());
    }
    
    @Test
    public void testGetSoftwareSuccess() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        int softwareId = 1;
        Software software = new Software();
        when(softwareService.getSoftwareById(softwareId)).thenReturn(software);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getSoftware(softwareId);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(software, responseEntity.getBody());
    }

    @Test
    public void testGetSoftwareSoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        int softwareId = 1;
        when(softwareService.getSoftwareById(softwareId)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getSoftware(softwareId);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Software not found", responseEntity.getBody());
    }
    
    @Test
    public void testGetSoftwareByNameSuccess() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        String softwareName = "Software1";
        Software software = new Software();
        when(softwareService.getSoftwareByName(softwareName)).thenReturn(software);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getSoftwareByName(softwareName);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(software, responseEntity.getBody());
    }

    @Test
    public void testGetSoftwareByNameSoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mocking the behavior of SoftwareService
        String softwareName = "NonExistingSoftware";
        when(softwareService.getSoftwareByName(softwareName)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getSoftwareByName(softwareName);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Software not found", responseEntity.getBody());
    }
    
    @Test
    void testGetSoftwareByLicenseKey_SoftwareFound() throws SoftwareNotFoundException {
        // Given
        String licenseKey = "abc123";
        List<Software> softwareList = new ArrayList<>();
        softwareList.add(new Software(/* software details */));
        when(softwareService.getSoftwareByLicenseKey(licenseKey)).thenReturn(softwareList);

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByLicenseKey(licenseKey);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwareList, response.getBody());
    }

    @Test
    void testGetSoftwareByLicenseKey_SoftwareNotFound() throws SoftwareNotFoundException {
        // Given
        String licenseKey = "def456";
        when(softwareService.getSoftwareByLicenseKey(licenseKey)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByLicenseKey(licenseKey);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetSoftwareByLicenseKey_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Given
        String licenseKey = "ghi789";
        when(softwareService.getSoftwareByLicenseKey(licenseKey)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByLicenseKey(licenseKey);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }

    
    @Test
    void testGetSoftwareByPurchaseDate_SoftwareFound() throws SoftwareNotFoundException {
        // Given
        LocalDate purchaseDate = LocalDate.now();
        List<Software> softwareList = new ArrayList<>();
        softwareList.add(new Software(/* software details */));
        when(softwareService.getSoftwareByPurchaseDate(purchaseDate)).thenReturn(softwareList);

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByPurchaseDate(purchaseDate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwareList, response.getBody());
    }

    @Test
    void testGetSoftwareByPurchaseDate_SoftwareNotFound() throws SoftwareNotFoundException {
        // Given
        LocalDate purchaseDate = LocalDate.now();
        when(softwareService.getSoftwareByPurchaseDate(purchaseDate)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByPurchaseDate(purchaseDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetSoftwareByPurchaseDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Given
        LocalDate purchaseDate = LocalDate.now();
        when(softwareService.getSoftwareByPurchaseDate(purchaseDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByPurchaseDate(purchaseDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    
    @Test
    void testGetSoftwareByExpirationDate_SoftwareFound() throws SoftwareNotFoundException {
        // Given
        LocalDate expirationDate = LocalDate.now();
        List<Software> softwareList = new ArrayList<>();
        softwareList.add(new Software(/* software details */));
        when(softwareService.getSoftwareByExpirationDate(expirationDate)).thenReturn(softwareList);

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByExpirationDate(expirationDate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwareList, response.getBody());
    }

    @Test
    void testGetSoftwareByExpirationDate_SoftwareNotFound() throws SoftwareNotFoundException {
        // Given
        LocalDate expirationDate = LocalDate.now();
        when(softwareService.getSoftwareByExpirationDate(expirationDate)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByExpirationDate(expirationDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetSoftwareByExpirationDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Given
        LocalDate expirationDate = LocalDate.now();
        when(softwareService.getSoftwareByExpirationDate(expirationDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByExpirationDate(expirationDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    
    @Test
    void testGetSoftwareByEndOfSupportDate_SoftwareFound() throws SoftwareNotFoundException {
        // Given
        LocalDate supportEndDate = LocalDate.now();
        List<Software> softwareList = new ArrayList<>();
        softwareList.add(new Software(/* software details */));
        when(softwareService.getSoftwareBySupportEndDate(supportEndDate)).thenReturn(softwareList);

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByEndOfSupportDate(supportEndDate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwareList, response.getBody());
    }

    @Test
    void testGetSoftwareByEndOfSupportDate_SoftwareNotFound() throws SoftwareNotFoundException {
        // Given
        LocalDate supportEndDate = LocalDate.now();
        when(softwareService.getSoftwareBySupportEndDate(supportEndDate)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByEndOfSupportDate(supportEndDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetSoftwareByEndOfSupportDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Given
        LocalDate supportEndDate = LocalDate.now();
        when(softwareService.getSoftwareBySupportEndDate(supportEndDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // When
        ResponseEntity<?> response = regularUserController.getSoftwareByEndOfSupportDate(supportEndDate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    @Test
    public void testGetLifeCycleEventSuccess() throws LifecycleEventNotFoundException {
        // Mocking the behavior of LifecycleeventService
        int eventId = 1;
        LifecycleEvent event = new LifecycleEvent();
        when(lifecycleeventService.getLifeCycleEventById(eventId)).thenReturn(event);

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getLifeCycleEvent(eventId);

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    @Test
    public void testGetLifeCycleEventLifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
        // Mocking the behavior of LifecycleeventService
        int eventId = 1;
        when(lifecycleeventService.getLifeCycleEventById(eventId)).thenThrow(new LifecycleEventNotFoundException("Lifecycle event not found"));

        // Calling the controller method
        ResponseEntity<?> responseEntity = regularUserController.getLifeCycleEvent(eventId);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Lifecycle event not found", responseEntity.getBody());
    }

    @Test
    void testGetLifeCycleEventByEventType_EventsFound() throws LifecycleEventNotFoundException {
        // Given
        String eventType = "Audit";
        List<LifecycleEvent> eventList = new ArrayList<>();
        eventList.add(new LifecycleEvent(/* event details */));
        when(lifecycleeventService.getLifeCycleEventByEventType(eventType)).thenReturn(eventList);

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventType(eventType);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventList, response.getBody());
    }

    @Test
    void testGetLifeCycleEventByEventType_EventsNotFound() throws LifecycleEventNotFoundException {
        // Given
        String eventType = "Maintenance";
        when(lifecycleeventService.getLifeCycleEventByEventType(eventType)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventType(eventType);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetLifeCycleEventByEventType_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
        // Given
        String eventType = "Repair";
        when(lifecycleeventService.getLifeCycleEventByEventType(eventType)).thenThrow(new LifecycleEventNotFoundException("Lifecycle event not found"));

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByEventType(eventType);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Lifecycle event not found", response.getBody());
    }

    @Test
    void testGetLifeCycleEventByDescription_EventsFound() throws LifecycleEventNotFoundException {
        // Given
        String description = "Event description";
        List<LifecycleEvent> eventList = new ArrayList<>();
        eventList.add(new LifecycleEvent(/* event details */));
        when(lifecycleeventService.getLifeCycleEventByDescription(description)).thenReturn(eventList);

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByDescription(description);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventList, response.getBody());
    }

    @Test
    void testGetLifeCycleEventByDescription_EventsNotFound() throws LifecycleEventNotFoundException {
        // Given
        String description = "Non-existing event description";
        when(lifecycleeventService.getLifeCycleEventByDescription(description)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByDescription(description);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetLifeCycleEventByDescription_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
        // Given
        String description = "Invalid event description";
        when(lifecycleeventService.getLifeCycleEventByDescription(description)).thenThrow(new LifecycleEventNotFoundException("Lifecycle event not found"));

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByDescription(description);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Lifecycle event not found", response.getBody());
    }
    @Test
    void testGetLifeCycleEventByCategory_EventsFound() throws LifecycleEventNotFoundException {
        // Given
        String category = "Event category";
        List<LifecycleEvent> eventList = new ArrayList<>();
        eventList.add(new LifecycleEvent(/* event details */));
        when(lifecycleeventService.getLifeCycleEventByCategory(category)).thenReturn(eventList);

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByCategory(category);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventList, response.getBody());
    }

    @Test
    void testGetLifeCycleEventByCategory_EventsNotFound() throws LifecycleEventNotFoundException {
        // Given
        String category = "Non-existing category";
        when(lifecycleeventService.getLifeCycleEventByCategory(category)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByCategory(category);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetLifeCycleEventByCategory_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
        // Given
        String category = "Invalid category";
        when(lifecycleeventService.getLifeCycleEventByCategory(category)).thenThrow(new LifecycleEventNotFoundException("Lifecycle event not found"));

        // When
        ResponseEntity<?> response = regularUserController.getLifeCycleEventByCategory(category);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Lifecycle event not found", response.getBody());
    }
}

    





