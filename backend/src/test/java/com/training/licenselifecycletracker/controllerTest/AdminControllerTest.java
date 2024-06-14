package com.training.licenselifecycletracker.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.controller.AdminController;
import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.entities.ERole;
import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.entities.Role;
import com.training.licenselifecycletracker.entities.Software;
import com.training.licenselifecycletracker.entities.User;
import com.training.licenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.licenselifecycletracker.exceptions.UserNotFoundException;
import com.training.licenselifecycletracker.service.DeviceService;
import com.training.licenselifecycletracker.service.LifecycleeventService;
import com.training.licenselifecycletracker.service.SoftwareService;
import com.training.licenselifecycletracker.service.UserService;
 
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
 
	@Mock
	private DeviceService deviceService;
	
	@Mock
	private SoftwareService softwareService;
	
	@Mock
	private LifecycleeventService lifecycleeventService;
 
	@Mock
	private UserService userService;
 
	@InjectMocks  
	private AdminController adminController;
	
	private Device device;
 
	@Test
	void testAddDevice_Success() {
	    // Mock data
	    Device device = new Device();
	    
	    // Mock the behavior of DeviceService
	    when(deviceService.addDevice(device)).thenReturn(device);

	    // Call the controller method
	    ResponseEntity<Device> response = adminController.addDevice(device);

	    // Verify the response
	    assertEquals(HttpStatus.OK, response.getStatusCode()); // Change HttpStatus.CREATED to HttpStatus.OK
	    assertEquals(device, response.getBody()); // Assuming your controller returns the added device in the response body
	    verify(deviceService).addDevice(device);
	}
	
	@Test
    void testViewDevices_Success() throws DeviceNotFoundException {
        // Mock data
        List<Device> devices = Arrays.asList(new Device(), new Device());

        // Mock the behavior of DeviceService
        when(deviceService.viewDevices()).thenReturn(ResponseEntity.ok(devices));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewDevices();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    void testViewDevices_Failure_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock the behavior of DeviceService to throw DeviceNotFoundException
        when(deviceService.viewDevices()).thenThrow(new DeviceNotFoundException("No Devices Found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewDevices();

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No Devices Found", response.getBody());
    }
    
    @Test
    void testGetDevice_Success() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 1;
        Device device = new Device();

        // Mock the behavior of DeviceService
        when(deviceService.getDeviceById(deviceId)).thenReturn(device);

        // Call the controller method
        ResponseEntity<?> response = adminController.getDevice(deviceId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    void testGetDevice_Failure_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 1;
        String errorMessage = "Device not found";

        // Mock the behavior of DeviceService to throw DeviceNotFoundException
        when(deviceService.getDeviceById(deviceId)).thenThrow(new DeviceNotFoundException(errorMessage));

        // Call the controller method
        ResponseEntity<?> response = adminController.getDevice(deviceId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
    
    @Test
    void testUpdateDevice_Success() throws DeviceNotFoundException {
        // Mock data
        Device device = new Device();
        device.setDeviceId(1); // Set a valid ID for the device

        // Mock the behavior of DeviceService
        when(deviceService.updateDevice(device)).thenReturn(device);

        // Call the controller method
        ResponseEntity<?> response = adminController.updateDevice(device);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    void testUpdateDevice_Failure_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock data
        Device device = new Device();
        device.setDeviceId(1); // Set a valid ID for the device
        String errorMessage = "Device not found";

        // Mock the behavior of DeviceService to throw DeviceNotFoundException
        when(deviceService.updateDevice(device)).thenThrow(new DeviceNotFoundException(errorMessage));

        // Call the controller method
        ResponseEntity<?> response = adminController.updateDevice(device);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
    
    @Test
    void testDeleteDeviceById_Success() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 1;
        String expectedResult = "Device deleted successfully";

        // Mock the behavior of DeviceService
        when(deviceService.deleteDeviceById(deviceId)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<?> response = adminController.deleteDeviceById(deviceId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testDeleteDeviceById_Failure_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock data
        int deviceId = 1;
        String errorMessage = "Device not found";

        // Mock the behavior of DeviceService to throw DeviceNotFoundException
        when(deviceService.deleteDeviceById(deviceId)).thenThrow(new DeviceNotFoundException(errorMessage));

        // Call the controller method
        ResponseEntity<?> response = adminController.deleteDeviceById(deviceId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
    
    @Test
    void testAddSoftware_Success() {
        // Mock data
        Software software = new Software();
        Software savedSoftware = new Software(); // Assuming it's correctly saved

        // Mock the behavior of SoftwareService
        when(softwareService.addSoftware(software)).thenReturn(savedSoftware);

        // Call the controller method
        ResponseEntity<Software> response = adminController.addSoftware(software);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedSoftware, response.getBody());
    }

    @Test
    void testGetSoftware_Success() throws SoftwareNotFoundException {
        // Mock data
        int softwareId = 1;
        Software software = new Software(); // Assuming it's found successfully

        // Mock the behavior of SoftwareService
        when(softwareService.getSoftwareById(softwareId)).thenReturn(software);

        // Call the controller method
        ResponseEntity<?> response = adminController.getSoftware(softwareId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(software, response.getBody());
    }

    @Test
    void testGetSoftware_Failure() throws SoftwareNotFoundException {
        // Mock data
        int softwareId = 1;

        // Mock the behavior of SoftwareService to throw SoftwareNotFoundException
        when(softwareService.getSoftwareById(softwareId)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.getSoftware(softwareId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    @Test
    void testViewSoftwares_Success() throws SoftwareNotFoundException {
        // Mock data
        List<Software> softwares = new ArrayList<>(); // Assuming some softwares are found successfully

        // Mock the behavior of SoftwareService
        when(softwareService.viewSoftwares()).thenReturn(ResponseEntity.ok(softwares));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewSoftwares();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(softwares, response.getBody());
    }

    @Test
    void testViewSoftwares_Failure() throws SoftwareNotFoundException {
        // Mock the behavior of SoftwareService to throw SoftwareNotFoundException
        when(softwareService.viewSoftwares()).thenThrow(new SoftwareNotFoundException("No software found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewSoftwares();

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No software found", response.getBody());
    }
    @Test
    void testUpdateSoftware_Success() throws SoftwareNotFoundException {
        // Mock data
        Software software = new Software(); // Assuming the software to update exists
        Software updatedSoftware = new Software(); // Assuming the software is successfully updated

        // Mock the behavior of SoftwareService
        when(softwareService.updateSoftware(software)).thenReturn(updatedSoftware);

        // Call the controller method
        ResponseEntity<Software> response = adminController.updateSoftware(software);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSoftware, response.getBody());
    }

    @Test
    void testUpdateSoftware_Failure() throws SoftwareNotFoundException {
        // Mock data
        Software software = new Software(); // Assuming the software to update exists

        // Mock the behavior of SoftwareService to throw SoftwareNotFoundException
        when(softwareService.updateSoftware(software)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Call the controller method
        ResponseEntity<Software> response = adminController.updateSoftware(software);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody()); // Body is null because we're returning ResponseEntity.notFound().build()
    }

    
    @Test
    void testDeleteSoftwareById_Success() throws SoftwareNotFoundException {
        // Mock data
        int softwareId = 1;
        String expectedResult = "Software deleted successfully";

        // Mock the behavior of SoftwareService
        when(softwareService.deleteSoftwareById(softwareId)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteSoftwareById(softwareId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testDeleteSoftwareById_Failure() throws SoftwareNotFoundException {
        // Mock data
        int softwareId = 1;

        // Mock the behavior of SoftwareService to throw SoftwareNotFoundException
        when(softwareService.deleteSoftwareById(softwareId)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteSoftwareById(softwareId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody()); // Body is null because we're returning ResponseEntity.notFound().build()
    }
    @Test
    void testAddLifecycleEvent_Success() {
        // Mock data
        LifecycleEvent lifecycleEvent = new LifecycleEvent();
        LifecycleEvent expectedResult = new LifecycleEvent(); // You can create an expected result if needed

        // Mock the behavior of LifecycleeventService
        when(lifecycleeventService.addLifeCycleEvent(lifecycleEvent)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<LifecycleEvent> response = adminController.addLifecycleEvent(lifecycleEvent);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testGetLifecycleEvent_Success() throws LifecycleEventNotFoundException {
        // Mock data
        int eventId = 1;
        LifecycleEvent expectedEvent = new LifecycleEvent();

        // Mock the behavior of LifecycleeventService
        when(lifecycleeventService.getLifeCycleEventById(eventId)).thenReturn(expectedEvent);

        // Call the controller method
        ResponseEntity<?> response = adminController.getLifecycleEvent(eventId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEvent, response.getBody());
    }

    @Test
    void testGetLifecycleEvent_Failure() throws LifecycleEventNotFoundException {
        // Mock data
        int eventId = 1;

        // Mock the behavior of LifecycleeventService to throw an exception
        when(lifecycleeventService.getLifeCycleEventById(eventId)).thenThrow(new LifecycleEventNotFoundException("Event not found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.getLifecycleEvent(eventId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Event not found", response.getBody());
    }

    
    @Test
    void testViewLifecycleEvent_Success() throws LifecycleEventNotFoundException {
        // Mock data
        List<LifecycleEvent> expectedEvents = Arrays.asList(new LifecycleEvent(), new LifecycleEvent());

        // Mock the behavior of LifecycleeventService
        when(lifecycleeventService.viewLifecycleEvents()).thenReturn(ResponseEntity.ok(expectedEvents));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewLifecycleEvent();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEvents, response.getBody());
    }

    @Test
    void testViewLifecycleEvent_Failure() throws LifecycleEventNotFoundException {
        // Mock the behavior of LifecycleeventService to throw an exception
        when(lifecycleeventService.viewLifecycleEvents()).thenThrow(new LifecycleEventNotFoundException("No events found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.viewLifecycleEvent();

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No events found", response.getBody());
    }
    @Test
    void testUpdateLifecycleEvent_Success() throws LifecycleEventNotFoundException {
        // Mock data
        LifecycleEvent lifecycleEvent = new LifecycleEvent();
        LifecycleEvent updatedEvent = new LifecycleEvent();

        // Mock the behavior of LifecycleeventService
        when(lifecycleeventService.updateLifeCycleEvent(lifecycleEvent)).thenReturn(updatedEvent);

        // Call the controller method
        ResponseEntity<LifecycleEvent> response = adminController.updateLifecycleEvent(lifecycleEvent);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEvent, response.getBody());
    }

    @Test
    void testUpdateLifecycleEvent_Failure() throws LifecycleEventNotFoundException {
        // Mock the behavior of LifecycleeventService to throw an exception
        when(lifecycleeventService.updateLifeCycleEvent(Mockito.any())).thenThrow(new LifecycleEventNotFoundException("Event not found"));

        // Call the controller method
        ResponseEntity<LifecycleEvent> response = adminController.updateLifecycleEvent(new LifecycleEvent());

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
    @Test
    void testDeleteLifecycleEventById_Success() throws LifecycleEventNotFoundException {
        // Mock data
        int eventId = 1;
        String expectedResult = "Event deleted successfully";

        // Mock the behavior of LifecycleeventService
        when(lifecycleeventService.deleteLifeCycleEventById(eventId)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteLifecycleEventById(eventId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testDeleteLifecycleEventById_Failure() throws LifecycleEventNotFoundException {
        // Mock the behavior of LifecycleeventService to throw an exception
        when(lifecycleeventService.deleteLifeCycleEventById(Mockito.anyInt())).thenThrow(new LifecycleEventNotFoundException("Event not found"));

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteLifecycleEventById(1);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
    
    @Test
    void testAddUser_Success() throws UserNotFoundException {
        // Mock data
        User user = new User();
        User expectedResult = new User(); // Assuming the service returns the added user

        // Mock the behavior of UserService
        when(userService.addUser(user)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<User> response = adminController.addUser(user);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testGetUserById_Success() throws UserNotFoundException {
        // Mock data
        int userId = 1;
        User user = new User();
        user.setUserId(userId); // Assuming the user with ID 1 exists

        // Mock the behavior of UserService
        when(userService.getUserById(userId)).thenReturn(user);

        // Call the controller method
        ResponseEntity<?> response = adminController.getUserById(userId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserById_NotFound() throws UserNotFoundException {
        // Mock data
        int userId = 1;

        // Mock the behavior of UserService to throw an exception
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        // Call the controller method
        ResponseEntity<?> response = adminController.getUserById(userId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found with ID: " + userId, response.getBody());
    }
    @Test
    void testDeleteUserById_Success() throws UserNotFoundException {
        // Mock data
        int userId = 1;
        String expectedResult = "User deleted successfully";

        // Mock the behavior of UserService
        when(userService.deleteUserById(userId)).thenReturn(expectedResult);

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteUserById(userId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    void testDeleteUserById_NotFound() throws UserNotFoundException {
        // Mock data
        int userId = 1;

        // Mock the behavior of UserService to throw an exception
        when(userService.deleteUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        // Call the controller method
        ResponseEntity<String> response = adminController.deleteUserById(userId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    void testUpdateUser_Success() throws UserNotFoundException {
        // Mock data
        User user = new User();
        user.setUserId(1); // Assuming user ID is set
        user.setUsername("John Doe"); // Assuming user name is set

        // Mock the behavior of UserService
        when(userService.updateUser(user)).thenReturn(user);

        // Call the controller method
        ResponseEntity<User> response = adminController.updateUser(user);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUser_NotFound() throws UserNotFoundException {
        // Mock data
        User user = new User();
        user.setUserId(1); // Assuming user ID is set
        user.setUsername("John Doe"); // Assuming user name is set

        // Mock the behavior of UserService to throw an exception
        when(userService.updateUser(user)).thenThrow(new UserNotFoundException("User not found"));

        // Call the controller method
        ResponseEntity<User> response = adminController.updateUser(user);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    void testUpdateUserRole_UserExists() throws UserNotFoundException {
        // Given
        Integer userId = 1;
        Role role = new Role();
        role.setId(1); // Change to Integer
        when(userService.updateRole(userId, role)).thenReturn("Role Updated Successfully!!!");

        // When
        ResponseEntity<String> response = adminController.updateUserRole(userId, role);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Role Updated Successfully!!!", response.getBody());
    }

    @Test
    void testUpdateUserRole_UserNotFound() throws UserNotFoundException {
        // Given
        Integer userId = 1;
        Role role = new Role();
        role.setId(1); // Change to Integer
        when(userService.updateRole(userId, role)).thenThrow(new UserNotFoundException("User not found"));

        // When
        ResponseEntity<String> response = adminController.updateUserRole(userId, role);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void testUpdateUserRole_InternalServerError() throws UserNotFoundException {
        // Given
        Integer userId = 1;
        Role role = new Role();
        role.setId(1); // Change to Integer
        when(userService.updateRole(userId, role)).thenThrow(new RuntimeException("Internal Server Error"));

        // When
        ResponseEntity<String> response = adminController.updateUserRole(userId, role);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the user role.", response.getBody());
    }
    
  
}
	
	
