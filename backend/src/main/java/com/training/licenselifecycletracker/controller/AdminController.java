package com.training.licenselifecycletracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.licenselifecycletracker.entities.Device;
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
import com.training.licenselifecycletracker.service.ReportService;
import com.training.licenselifecycletracker.service.SoftwareService;
import com.training.licenselifecycletracker.service.UserService;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)

public class AdminController {

    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private SoftwareService softwareService;
    
    @Autowired
    private LifecycleeventService lifecycleeventService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReportService reportService;
    
    
	

    // Create Devices
    
    @PostMapping("/addDevices")
    
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
    	//convert device model to Device entity
        Device devices = deviceService.addDevice(device);
        return ResponseEntity.ok(devices);
    }
    

    
    @GetMapping("/getalldevices")
    public ResponseEntity<?> viewDevices() {
        try {
            List<Device> devices = (List<Device>) deviceService.viewDevices().getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    // get device by id
    @GetMapping("/getdevice")
	public ResponseEntity<?> getDevice(@RequestParam("id") Integer deviceId) {
	    try {
	        Device device = deviceService.getDeviceById(deviceId);
	        return ResponseEntity.ok(device);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

    // Update Device
    @PutMapping("/updateDevices")
    public ResponseEntity<?> updateDevice(@RequestBody Device device) {
        try {
            Device updatedDevice = deviceService.updateDevice(device);
            return ResponseEntity.ok(updatedDevice);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete device
    @PostMapping("/deletedevice/{deviceId}")
    public ResponseEntity<?> deleteDeviceById(@PathVariable Integer deviceId) {
        try {
            String result = deviceService.deleteDeviceById(deviceId);
            return ResponseEntity.ok(result);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
   
    
    // Create Software
    

    
    @PostMapping("/addSoftware")
    public ResponseEntity<Software> addSoftware(@RequestBody Software software) {
        Software savedSoftware = softwareService.addSoftware(software);
        return ResponseEntity.ok(savedSoftware);
    }

    
    
    // get software by id
    
    @GetMapping("/getsoftware")
	public ResponseEntity<?> getSoftware(@RequestParam("id") Integer softwareId) {
	    try {
	        Software software = softwareService.getSoftwareById(softwareId);
	        return ResponseEntity.ok(software);
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
    
    @GetMapping("/getallsoftwares")
	public ResponseEntity<?> viewSoftwares() {
	    try {
	        List<Software> softwares = (List<Software>) softwareService.viewSoftwares().getBody();
	        return ResponseEntity.ok(softwares);
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

    
    
    @PutMapping("/updatesoftware")
    public ResponseEntity<Software> updateSoftware(@RequestBody Software software) {
        try {
            Software updatedSoftware = softwareService.updateSoftware(software);
            return ResponseEntity.ok(updatedSoftware);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deletesoftware/{softwareId}")
    public ResponseEntity<String> deleteSoftwareById(@PathVariable Integer softwareId) {
        try {
            String result = softwareService.deleteSoftwareById(softwareId);
            return ResponseEntity.ok(result);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    

      
   // Create LifecycleEvent

    
    @PostMapping("/addlifecycleevent")
    public ResponseEntity<LifecycleEvent> addLifecycleEvent(@RequestBody LifecycleEvent lifecycleEvent) {
        LifecycleEvent savedEvent = lifecycleeventService.addLifeCycleEvent(lifecycleEvent);
        return ResponseEntity.ok(savedEvent);
    }
    
    
    @GetMapping("/getlifecycleevent")
   	public ResponseEntity<?> getLifecycleEvent(@RequestParam("id") Integer eventId) {
   	    try {
   	    	LifecycleEvent savedEvent = lifecycleeventService.getLifeCycleEventById(eventId);
   	        return ResponseEntity.ok(savedEvent);
   	    } catch (LifecycleEventNotFoundException ex) {
   	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
   	    }
   	}
    
    
    @GetMapping("/getalllifecycleevents")
	public ResponseEntity<?> viewLifecycleEvent() {
	    try {
	        List<LifecycleEvent> softwares = (List<LifecycleEvent>) lifecycleeventService. viewLifecycleEvents().getBody();
	        return ResponseEntity.ok(softwares);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
    
    @PutMapping("/updatelifecycleevent")
    public ResponseEntity<LifecycleEvent> updateLifecycleEvent(@RequestBody LifecycleEvent lifecycleEvent) {
        try {
        	LifecycleEvent updateEvent = lifecycleeventService.updateLifeCycleEvent(lifecycleEvent);
            return ResponseEntity.ok(updateEvent);
        } catch (LifecycleEventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deletelifecyclevent/{eventId}")
    public ResponseEntity<String> deleteLifecycleEventById(@PathVariable Integer eventId) {
        try {
            String result = lifecycleeventService.deleteLifeCycleEventById(eventId);
            return ResponseEntity.ok(result);
        } catch (LifecycleEventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
      
      
      
      // user
      
      

    
    @PostMapping("/adduser")
      public ResponseEntity<User> addUser(@RequestBody User user) throws UserNotFoundException {
      User addedUser = userService.addUser(user);
      return ResponseEntity.ok(addedUser);
      }
    

    @GetMapping("/getuserbyid")
    public ResponseEntity<?> getUserById(@RequestParam("id") Integer userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }
    }

  

    @PostMapping("/deleteuser/{userId}")
    public ResponseEntity<String> deleteUserById(@RequestParam Integer userId) {
        try {
            String result = userService.deleteUserById(userId);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateuser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PutMapping("/users/updaterole")
    public ResponseEntity<String> updateUserRole(@RequestParam Integer userId, @RequestBody Role role) {
        try {
            String result = userService.updateRole(userId, role);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user role.");
        }
    }
     
    
    
    
}
