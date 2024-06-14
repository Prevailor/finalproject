package com.training.licenselifecycletracker.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.licenselifecycletracker.repositories.DeviceRepository;
import com.training.licenselifecycletracker.service.DeviceServiceImpl;

public class DeviceServiceImplTest {

    @Mock
    private DeviceRepository repo;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewDevices_Success() throws DeviceNotFoundException {
        // Mock the repository behavior
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        when(repo.findAll()).thenReturn(devices);

        // Call the service method
        ResponseEntity<List<Device>> responseEntity = deviceService.viewDevices();

        // Assert the response entity status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the response entity body
        List<Device> result = responseEntity.getBody();
        assertEquals(devices, result);
    }

    @Test
    public void testViewDevices_DeviceNotFound() {
        // Mock the repository behavior to return an empty list
        when(repo.findAll()).thenReturn(new ArrayList<>());

        // Call the service method and assert that it throws DeviceNotFoundException
        assertThrows(DeviceNotFoundException.class, () -> deviceService.viewDevices());
    }

    @Test
    public void testAddDevice() {
        Device device = new Device();
        when(repo.save(device)).thenReturn(device);

        Device result = deviceService.addDevice(device);

        assertEquals(device, result);
        verify(repo, times(1)).save(device);
    }


    @Test
    public void testGetDeviceById() throws DeviceNotFoundException {
        Device device = new Device();
        when(repo.findById(1)).thenReturn(Optional.of(device));

        Device result = deviceService.getDeviceById(1);

        assertEquals(device, result);
    }

    @Test
    public void testGetDeviceById_NotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceById(1);
        });
    }
    @Test
    public void testGetDeviceByName() throws DeviceNotFoundException {
        // Mocked device list with devices having the name "TestDevice"
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        when(repo.findByDeviceName("TestDevice")).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByName("TestDevice");

        assertEquals(deviceList, result);
    }

    @Test
    public void testGetDeviceByName_NotFound() {
        // Mocked empty device list for the name "TestDevice"
        List<Device> deviceList = new ArrayList<>();
        when(repo.findByDeviceName("TestDevice")).thenReturn(deviceList);

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByName("TestDevice");
        });
    }


    @Test
    public void testGetDeviceByDeviceType() throws DeviceNotFoundException {
        // Mocked device list by type
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        when(repo.findByDeviceType("TestType")).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByDeviceType("TestType");

        assertEquals(deviceList, result);
    }

    // Similar for other methods

    @Test
    public void testGetDeviceByDeviceType_NotFound() {
        // Mocked empty device list by type
        List<Device> deviceList = new ArrayList<>();
        when(repo.findByDeviceType("NonExistentType")).thenReturn(deviceList);

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByDeviceType("NonExistentType");
        });
    }

    @Test
    public void testGetDeviceByStatus() throws DeviceNotFoundException {
        // Mocked device list with status "Active"
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        when(repo.findByStatus("Active")).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByStatus("Active");

        assertEquals(deviceList, result);
    }

    @Test
    public void testGetDeviceByStatus_NotFound() {
        // Mocked empty device list for status "Active"
        List<Device> deviceList = new ArrayList<>();
        when(repo.findByStatus("Active")).thenReturn(deviceList);

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByStatus("Active");
        });
    }


    @Test
    public void testUpdateDevice() throws DeviceNotFoundException {
        // Create a device with a non-null ID
        Device device = new Device();
        device.setDeviceId(1); // Set a valid ID

        // Mock repository to return the device when findById is called
        when(repo.findById(1)).thenReturn(Optional.of(device));
        // Mock repository to return the device when save is called
        when(repo.save(device)).thenReturn(device);

        // Call the updateDevice method with the device
        Device result = deviceService.updateDevice(device);

        // Assert that the result matches the device
        assertEquals(device, result);
    }

    @Test
    public void testUpdateDevice_NotFound() {
        Device device = new Device();
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.updateDevice(device);
        });
    }

    @Test
    public void testDeleteDeviceById() throws DeviceNotFoundException {
        Device device = new Device();
        when(repo.findById(1)).thenReturn(Optional.of(device));

        String result = deviceService.deleteDeviceById(1);

        assertEquals("Device with Id 1 deleted successfully", result);
        verify(repo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteDeviceById_NotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.deleteDeviceById(1);
        });
    }

    @Test
    public void testGetDeviceByPurchaseDate() throws DeviceNotFoundException {
        // Mocked device list with purchase date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        LocalDate purchaseDate = LocalDate.now();
        when(repo.findByPurchaseDate(purchaseDate)).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByPurchaseDate(purchaseDate);

        assertEquals(deviceList, result);
    }

    @Test
    public void testGetDeviceByPurchaseDate_NotFound() {
        // Mocked empty device list for purchase date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        LocalDate purchaseDate = LocalDate.now();
        when(repo.findByPurchaseDate(purchaseDate)).thenReturn(Collections.emptyList());

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByPurchaseDate(purchaseDate);
        });
    }


    @Test
    public void testGetDeviceByExpirationDate() throws DeviceNotFoundException {
        // Mocked device list with expiration date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        LocalDate expirationDate = LocalDate.now();
        when(repo.findByExpirationDate(expirationDate)).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByExpirationDate(expirationDate);

        assertEquals(deviceList, result);
    }

    @Test
    public void testGetDeviceByExpirationDate_NotFound() {
        // Mocked empty device list for expiration date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        LocalDate expirationDate = LocalDate.now();
        when(repo.findByExpirationDate(expirationDate)).thenReturn(deviceList);

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByExpirationDate(expirationDate);
        });
    }


    @Test
    public void testGetDeviceByEndOfSupportDate() throws DeviceNotFoundException {
        // Mocked device list with end of support date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device()); // Add devices to the list if you want to test scenarios with devices found
        LocalDate endOfSupportDate = LocalDate.now();
        when(repo.findByEndOfSupportDate(endOfSupportDate)).thenReturn(deviceList);

        List<Device> result = deviceService.getDeviceByEndOfSupportDate(endOfSupportDate);

        assertEquals(deviceList, result);
    }

    @Test
    public void testGetDeviceByEndOfSupportDate_NotFound() {
        // Mocked empty device list for end of support date matching LocalDate.now()
        List<Device> deviceList = new ArrayList<>();
        LocalDate endOfSupportDate = LocalDate.now();
        when(repo.findByEndOfSupportDate(endOfSupportDate)).thenReturn(deviceList);

        assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByEndOfSupportDate(endOfSupportDate);
        });
    }

}
