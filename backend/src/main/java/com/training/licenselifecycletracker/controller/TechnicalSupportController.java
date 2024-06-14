package com.training.licenselifecycletracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.licenselifecycletracker.dto.LogFaultRequestDTO;
import com.training.licenselifecycletracker.dto.UpdateFaultLogRequestDTO;
import com.training.licenselifecycletracker.service.TechnicalSupportService;
 
@RestController
@RequestMapping("/api/technicalsupport")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TechnicalSupportController {
    
	@Autowired
    TechnicalSupportService technicalSupportService;
 
	@PostMapping("/support/faults")
	public ResponseEntity<String> logFault(@RequestBody LogFaultRequestDTO logFaultRequest) {
	    try {
	        technicalSupportService.logFault(logFaultRequest.getDeviceId(), logFaultRequest.getDescription(), logFaultRequest.getDate(), logFaultRequest.getCategory());
	        return ResponseEntity.ok("Fault logged successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging fault: " + e.getMessage());
	    }
	}
 
	@PostMapping("/support/faults/deviceId")
	public ResponseEntity<String> updateFaultLog(@RequestBody UpdateFaultLogRequestDTO updateFaultLogRequest) {
	    try {
	        technicalSupportService.updateFaultLog(updateFaultLogRequest.getDeviceId(), updateFaultLogRequest.getRepairDetails(), updateFaultLogRequest.getCategory(), updateFaultLogRequest.getEventType());
	        return ResponseEntity.ok("Fault log updated successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating fault log: " + e.getMessage());
	    }
	}
 
    @GetMapping("/support/dates")
    public ResponseEntity<List<String>> viewEndOfSupportDates() {
        try {
            List<String> supportDates = technicalSupportService.viewEndOfSupportDates();
            return ResponseEntity.ok(supportDates);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
 