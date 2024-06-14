package com.training.licenselifecycletracker.serviceTest;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.training.licenselifecycletracker.entities.Device;
//import com.training.licenselifecycletracker.entities.LifecycleEvent;
//import com.training.licenselifecycletracker.entities.Software;
//import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
//import com.training.licenselifecycletracker.repositories.DeviceRepository;
//import com.training.licenselifecycletracker.repositories.LifecycleEventRepository;
//import com.training.licenselifecycletracker.repositories.SoftwareRepository;
//import com.training.licenselifecycletracker.service.TechnicalSupportServiceImpl;
//
//@SpringBootTest
//public class TechnicalSupportServiceImplTest {
//
//    @Mock
//    private DeviceRepository deviceRepository;
//
//    @Mock
//    private SoftwareRepository softwareRepository;
//
//    @Mock
//    private LifecycleEventRepository lifecycleEventRepository;
//
//    @InjectMocks
//    private TechnicalSupportServiceImpl technicalSupportService;
//
//    private Device device;
//    private Software software;
//
//    @BeforeEach
//    public void setUp() {
//        device = new Device();
//        device.setDeviceId(1);
//        device.setDeviceName("Test Device");
//        device.setEndOfSupportDate(LocalDate.now().plusMonths(6));
//
//        software = new Software();
//        software.setSoftwareId(1);
//        software.setSoftwareName("Test Software");
//        software.setSupportEndDate(LocalDate.now().plusMonths(3));
//    }
//
//    @Test
//    public void testLogFault_Success() throws LifecycleEventNotFoundException {
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//        when(lifecycleEventRepository.findByRelatedId(1)).thenReturn(null);
//
//        technicalSupportService.logFault(1, "Test Description", LocalDate.now().toString(), "TestCategory");
//
//        verify(lifecycleEventRepository, times(1)).save(any(LifecycleEvent.class));
//    }
//
//    @Test
//    public void testLogFault_DeviceNotFound() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            technicalSupportService.logFault(100, "Test Description", LocalDate.now().toString(), "TestCategory");
//        });
//    }
//
//    // Write more test cases for logFault method covering other scenarios
//
//    @Test
//    public void testUpdateFaultLog_Success() {
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//        when(lifecycleEventRepository.findByRelatedIdAndEventType(1, "Resolved")).thenReturn(Optional.of(new LifecycleEvent()));
//
//        technicalSupportService.updateFaultLog(1, "Test Repair Details", "TestCategory", "Resolved");
//
//        verify(lifecycleEventRepository, times(1)).save(any(LifecycleEvent.class));
//    }
//
//    @Test
//    public void testUpdateFaultLog_DeviceNotFound() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            technicalSupportService.updateFaultLog(100, "Test Repair Details", "TestCategory", "Resolved");
//        });
//    }
//
//    // Write more test cases for updateFaultLog method covering other scenarios
//
//    @Test
//    public void testViewEndOfSupportDates() {
//        List<Device> devices = new ArrayList<>();
//        devices.add(device);
//        when(deviceRepository.findAll()).thenReturn(devices);
//
//        List<Software> softwares = new ArrayList<>();
//        softwares.add(software);
//        when(softwareRepository.findAll()).thenReturn(softwares);
//
//        List<String> supportDates = technicalSupportService.viewEndOfSupportDates();
//
//        assertEquals(2, supportDates.size()); // Assuming there's one device and one software
//        // Write more assertions based on your requirements
//    }
//
//    // Add more test cases for other methods as needed
//    
//    @Test
//    public void testLogFault_FaultEventNotNull() throws LifecycleEventNotFoundException {
//        // Mocking behavior of repositories
//        Device device = new Device();
//        device.setDeviceId(1);
//
//        LifecycleEvent existingFaultEvent = new LifecycleEvent();
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//        when(lifecycleEventRepository.findByRelatedId(1)).thenReturn(existingFaultEvent);
//
//        // Invoking the method
//        technicalSupportService.logFault(1, "Description", "2024-06-14", "Category");
//
//        // Verifying that existing fault event is updated
//        verify(lifecycleEventRepository).save(existingFaultEvent);
//    }
//
//    @Test
//    public void testLogFault_FaultEventNull() throws LifecycleEventNotFoundException {
//        // Mocking behavior of repositories
//        Device device = new Device();
//        device.setDeviceId(1);
//
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//        when(lifecycleEventRepository.findByRelatedId(1)).thenReturn(null);
//
//        // Invoking the method
//        technicalSupportService.logFault(1, "Description", "2024-06-14", "Category");
//
//        // Verifying that a new fault event is created
//        verify(lifecycleEventRepository).save(any(LifecycleEvent.class));
//    }
//    
//    @Test
//    public void testUpdateFaultLog_ExistingFaultLogFound() {
//        // Mock the behavior of device repository to return a device
//        Device device = new Device();
//        device.setDeviceId(1);
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//
//        // Mock the behavior of lifecycle event repository to return an existing fault log
//        LifecycleEvent existingFaultEvent = new LifecycleEvent();
//        when(lifecycleEventRepository.findByRelatedIdAndEventType(1, "Fault")).thenReturn(Optional.of(existingFaultEvent));
//
//        // Call the method under test
//        technicalSupportService.updateFaultLog(1, "Repair Details", "Category", "Fault");
//
//        // Verify that the existing fault log is updated and saved
//        assertEquals("Resolved", existingFaultEvent.getEventType());
//        assertEquals("Repair Details", existingFaultEvent.getDescription());
//        assertEquals("Category", existingFaultEvent.getCategory());
//        verify(lifecycleEventRepository).save(existingFaultEvent);
//    }
//
//    @Test
//    public void testUpdateFaultLog_NoExistingFaultLogFound() {
//        // Mock the behavior of device repository to return a device
//        Device device = new Device();
//        device.setDeviceId(1);
//        when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
//
//        // Mock the behavior of lifecycle event repository to return no existing fault log
//        when(lifecycleEventRepository.findByRelatedIdAndEventType(1, "Fault")).thenReturn(Optional.empty());
//
//        // Call the method under test and verify that it throws IllegalStateException
//        assertThrows(IllegalStateException.class, () -> {
//            technicalSupportService.updateFaultLog(1, "Repair Details", "Category", "Fault");
//        });
//
//        // Verify that lifecycle event repository save method is not called
//        verify(lifecycleEventRepository, never()).save(any());
//    }
//}
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.entities.Software;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.repositories.DeviceRepository;
import com.training.licenselifecycletracker.repositories.LifecycleEventRepository;
import com.training.licenselifecycletracker.repositories.SoftwareRepository;
import com.training.licenselifecycletracker.service.TechnicalSupportServiceImpl;
 
public class TechnicalSupportServiceImplTest {
 
    @Mock
    private DeviceRepository deviceRepository;
 
    @Mock
    private SoftwareRepository softwareRepository;
 
    @Mock
    private LifecycleEventRepository lifecycleEventRepository;
 
    @InjectMocks
    private TechnicalSupportServiceImpl technicalSupportService;
    @Captor
    private ArgumentCaptor<LifecycleEvent> lifecycleEventCaptor;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testLogFault() {
        Integer deviceId = 1;
        String description = "Device fault description";
        String date = "2024-06-12";
        String category = "Hardware";
 
        Device device = new Device();
        device.setDeviceId(deviceId);
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
 
        // Mock the behavior of lifecycleEventRepository.findByAssetId to return a valid LifecycleEvent
        LifecycleEvent faultEvent = new LifecycleEvent(); // Create a dummy LifecycleEvent
        when(lifecycleEventRepository.findByRelatedId(deviceId)).thenReturn(faultEvent);
 
        // Call the method under test
        try {
            technicalSupportService.logFault(deviceId, description, date, category);
        } catch (IllegalArgumentException | LifecycleEventNotFoundException e) {
            e.printStackTrace();
        }
 
        // Verify that the save method was called with the expected argument
        verify(lifecycleEventRepository, times(1)).save(any(LifecycleEvent.class));
    }
 
 
    @Test
    public void testLogFault_DeviceNotFound() {
        Integer deviceId = 1;
        String description = "Device fault description";
        String date = "2024-06-12";
        String category = "Hardware";
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());
 
        assertThrows(IllegalArgumentException.class, () -> {
            technicalSupportService.logFault(deviceId, description, date, category);
        });
    }
 
    @Test
    public void testUpdateFaultLog() {
        Integer deviceId = 1;
        String repairDetails = "Repair details";
        String category = "Hardware";
        String eventType = "EventType";
 
        Device device = new Device();
        device.setDeviceId(deviceId);
 
        LifecycleEvent existingEvent = new LifecycleEvent();
        existingEvent.setRelatedId(deviceId);
        existingEvent.setEventType(eventType);
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
        when(lifecycleEventRepository.findByRelatedIdAndEventType(deviceId, eventType)).thenReturn(Optional.of(existingEvent));
 
        technicalSupportService.updateFaultLog(deviceId, repairDetails, category, eventType);
 
        verify(lifecycleEventRepository, times(1)).save(any(LifecycleEvent.class));
    }
 
    @Test
    public void testUpdateFaultLog_DeviceNotFound() {
        Integer deviceId = 1;
        String repairDetails = "Repair details";
        String category = "Hardware";
        String eventType = "EventType";
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());
 
        assertThrows(IllegalArgumentException.class, () -> {
            technicalSupportService.updateFaultLog(deviceId, repairDetails, category, eventType);
        });
    }
 
    @Test
    public void testUpdateFaultLog_NoExistingFaultLog() {
        Integer deviceId = 1;
        String repairDetails = "Repair details";
        String category = "Hardware";
        String eventType = "EventType";
 
        Device device = new Device();
        device.setDeviceId(deviceId);
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
        when(lifecycleEventRepository.findByRelatedIdAndEventType(deviceId, eventType)).thenReturn(Optional.empty());
 
        assertThrows(IllegalStateException.class, () -> {
            technicalSupportService.updateFaultLog(deviceId, repairDetails, category, eventType);
        });
    }
 
    @Test
    public void testViewEndOfSupportDates() {
        List<Device> deviceList = new ArrayList<>();
        List<Software> softwareList = new ArrayList<>();
 
        Device device1 = new Device();
        device1.setDeviceId((int) 1L);
        device1.setDeviceName("Device1");
        device1.setEndOfSupportDate(LocalDate.now());
 
        Device device2 = new Device();
        device2.setDeviceId((int) 2L);
        device2.setDeviceName("Device2");
        device2.setEndOfSupportDate(LocalDate.now().plusDays(1));
 
        deviceList.add(device1);
        deviceList.add(device2);
 
        Software software1 = new Software();
        software1.setSoftwareId((int) 1L);
        software1.setSoftwareName("Software1");
        software1.setSupportEndDate(LocalDate.now());
 
        Software software2 = new Software();
        software2.setSoftwareId((int) 2L);
        software2.setSoftwareName("Software2");
        software2.setSupportEndDate(LocalDate.now().plusDays(1));
 
        softwareList.add(software1);
        softwareList.add(software2);
 
        when(deviceRepository.findAll()).thenReturn(deviceList);
        when(softwareRepository.findAll()).thenReturn(softwareList);
 
        List<String> supportDates = technicalSupportService.viewEndOfSupportDates();
 
        assertEquals(4, supportDates.size());
    }
 
    @Test
    public void testViewEndOfSupportDates_NoDevicesOrSoftwareFound() {
        when(deviceRepository.findAll()).thenReturn(Collections.emptyList());
        when(softwareRepository.findAll()).thenReturn(Collections.emptyList());
 
        List<String> supportDates = technicalSupportService.viewEndOfSupportDates();
 
        assertEquals(0, supportDates.size());
    }
    @Test
    public void testLogFault_LifecycleEventNotFound() {
        Integer deviceId = 1;
        String description = "Device fault description";
        String date = "2024-06-12";
        String category = "Hardware";
 
        Device device = new Device();
        device.setDeviceId(deviceId);
 
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
        when(lifecycleEventRepository.findByRelatedId(deviceId)).thenReturn(null);
 
        assertThrows(LifecycleEventNotFoundException.class, () -> {
            technicalSupportService.logFault(deviceId, description, date, category);
        });
    }
}