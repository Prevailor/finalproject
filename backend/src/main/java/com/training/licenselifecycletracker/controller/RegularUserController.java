package com.training.licenselifecycletracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/api/regularuser")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegularUserController {
	
	@Autowired
    private DeviceService deviceService;
	
	@Autowired
    private SoftwareService softwareService;
	
	@Autowired
    private LifecycleeventService lifecycleeventService;
	
	@Autowired
    private UserService userService;
	
	
	// Retrieve for Device
	
	

	
	 @GetMapping("/getalldevices")
	    public ResponseEntity<?> viewDevices() {
	        try {
	            List<Device> devices = (List<Device>) deviceService.viewDevices().getBody();
	            return ResponseEntity.ok(devices);
	        } catch (DeviceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	

	@GetMapping("/getdevice")
	public ResponseEntity<?> getDevice(@RequestParam Integer deviceId) {
	    try {
	        Device device = deviceService.getDeviceById(deviceId);
	        return ResponseEntity.ok(device);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getdevicebyname")
	public ResponseEntity<?> getDeviceByName(@RequestParam String deviceName) {
	    try {
	    	List<Device> device = deviceService.getDeviceByName(deviceName);
	        return ResponseEntity.ok(device);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	
	
	


	@GetMapping("/getdevicesbystatus")
	public ResponseEntity<?> getDevicesByStatus(@RequestParam String deviceStatus) {
	    try {
	        List<Device> devices = deviceService.getDeviceByStatus(deviceStatus);
	        return ResponseEntity.ok(devices);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	


	@GetMapping("/getdevicesbydevicetype")
	public ResponseEntity<?> getDevicesByDeviceType(@RequestParam String deviceType) {
	    try {
	        List<Device> devices = deviceService.getDeviceByDeviceType(deviceType);
	        return ResponseEntity.ok(devices);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getdevicesbypurchasedate")
	public ResponseEntity<?> getDevicesByPurchaseDate(@RequestParam LocalDate purchaseDate) {
	    try {
	        List<Device> devices = deviceService.getDeviceByPurchaseDate(purchaseDate);
	        return ResponseEntity.ok(devices);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getdevicesbyexpirationdate")
	public ResponseEntity<?> getDevicesByExpirationDate(@RequestParam LocalDate expirationDate) {
	    try {
	        List<Device> devices = deviceService.getDeviceByExpirationDate(expirationDate);
	        return ResponseEntity.ok(devices);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getdevicesbyendofsupportdate")
	public ResponseEntity<?> getDevicesByEndOfSupportDate(@RequestParam LocalDate endOfSupportDate) {
	    try {
	        List<Device> devices = deviceService.getDeviceByEndOfSupportDate(endOfSupportDate);
	        return ResponseEntity.ok(devices);
	    } catch (DeviceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	
	
	
        // Retrieve for Software

	@GetMapping("/getallsoftwares")
	public ResponseEntity<?> viewSoftwares() {
	    try {
	        List<Software> softwares = (List<Software>) softwareService.viewSoftwares().getBody();
	        return ResponseEntity.ok(softwares);
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getsoftware")
	public ResponseEntity<?> getSoftware(@RequestParam Integer softwareId) {
	    try {
	        Software software = softwareService.getSoftwareById(softwareId);
	        return ResponseEntity.ok(software);
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getsoftwarebyname")
	public ResponseEntity<?> getSoftwareByName(@RequestParam String softwareName) {
	    try {
	        Software software = softwareService.getSoftwareByName(softwareName);
	        return ResponseEntity.ok(software);
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

//	@GetMapping("/getsoftwarebystatus")
//	public ResponseEntity<?> getSoftwareByStatus(@RequestParam String status) {
//	    try {
//	        List<Software> softwares = softwareService.getSoftwareByStatus(status);
//	        if (!softwares.isEmpty()) {
//	            return ResponseEntity.ok(softwares);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    } catch (SoftwareNotFoundException ex) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	    }
//	}
	
	@GetMapping("/getsoftwarebystatus")
	public ResponseEntity<?> getSoftwareByStatus(@RequestParam String status) {
	    try {
	        List<Software> softwares = softwareService.getSoftwareByStatus(status);
	        if (!softwares.isEmpty()) {
	            return ResponseEntity.ok(softwares);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No software found with status: " + status);
	        }
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}


	@GetMapping("/getsoftwarebylicensekey")
	public ResponseEntity<?> getSoftwareByLicenseKey(@RequestParam String licenseKey) {
	    try {
	        List<Software> software = softwareService.getSoftwareByLicenseKey(licenseKey);
	        if (!software.isEmpty()) {
	            return ResponseEntity.ok(software);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getsoftwarebypurchasedate")
	public ResponseEntity<?> getSoftwareByPurchaseDate(@RequestParam LocalDate purchaseDate) {
	    try {
	        List<Software> software = softwareService.getSoftwareByPurchaseDate(purchaseDate);
	        if (!software.isEmpty()) {
	            return ResponseEntity.ok(software);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getsoftwarebyexpirationdate")
	public ResponseEntity<?> getSoftwareByExpirationDate(@RequestParam LocalDate expirationDate) {
	    try {
	        List<Software> software = softwareService.getSoftwareByExpirationDate(expirationDate);
	        if (!software.isEmpty()) {
	            return ResponseEntity.ok(software);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getsoftwarebyendofsupportdate")
	public ResponseEntity<?> getSoftwareByEndOfSupportDate(@RequestParam LocalDate supportEndDate) {
	    try {
	        List<Software> software = softwareService.getSoftwareBySupportEndDate(supportEndDate);
	        if (!software.isEmpty()) {
	            return ResponseEntity.ok(software);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (SoftwareNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	
	
	

	//Retriev for LifeCycleEvent
	


	@GetMapping("/getlifecycleevent")
	public ResponseEntity<?> getLifeCycleEvent(@RequestParam("id") Integer eventId) {
	    try {
	        LifecycleEvent event = lifecycleeventService.getLifeCycleEventById(eventId);
	        return ResponseEntity.ok(event);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getLifeCycleEventByEventType")
	public ResponseEntity<?> getLifeCycleEventByEventType(@RequestParam String eventType) {
	    try {
	        List<LifecycleEvent> events = lifecycleeventService.getLifeCycleEventByEventType(eventType);
	        return events.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(events);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getLifeCycleEventByEventDate")
	public ResponseEntity<?> getLifeCycleEventByEventDate(@RequestParam LocalDate eventDate) {
	    try {
	        List<LifecycleEvent> events = lifecycleeventService.getLifeCycleEventByEventDate(eventDate);
	        return events.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(events);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getLifeCycleEventByDescription")
	public ResponseEntity<?> getLifeCycleEventByDescription(@RequestParam String description) {
	    try {
	        List<LifecycleEvent> events = lifecycleeventService.getLifeCycleEventByDescription(description);
	        return events.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(events);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	@GetMapping("/getLifeCycleEventByCategory")
	public ResponseEntity<?> getLifeCycleEventByCategory(@RequestParam String category) {
	    try {
	        List<LifecycleEvent> events = lifecycleeventService.getLifeCycleEventByCategory(category);
	        return events.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(events);
	    } catch (LifecycleEventNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	
	
	
	
    //  user
    
//    @GetMapping("getuserbyid")
//    public ResponseEntity<User> getUserById(@PathVariable Integer userId) throws UserNotFoundException {
//        User user = userService.getUserById(userId);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return null;
//        }
//    }
	
	
	
//    
//    @GetMapping("/getuserbyid")
//    public ResponseEntity<?> getUserById(@PathVariable Integer userId) {
//        try {
//            User user = userService.getUserById(userId);
//            return ResponseEntity.ok(user);
//        } catch (UserNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }
    
    



    // notifications

//	@GetMapping("/notifications")
//	public void receiveNotifications() {
//	    // Call corresponding service method
//	    softwareService.receiveNotifications();
//	}
		
		
}
	

