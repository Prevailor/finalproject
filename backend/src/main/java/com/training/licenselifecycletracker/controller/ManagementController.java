package com.training.licenselifecycletracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.licenselifecycletracker.service.LifecycleeventService;

@RestController
@RequestMapping("/api/management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ManagementController {
	
	
	@Autowired
    private LifecycleeventService lifecycleeventService;
	
	
	@GetMapping("/getallLifecycleEvents")
    public ResponseEntity<?> viewLifecycleEvents() {
        try {
            List<LifecycleEvent> devices = (List<LifecycleEvent>) lifecycleeventService.viewLifecycleEvent().getBody();
            return ResponseEntity.ok(devices);
        } catch (LifecycleEventNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
	
	
	@GetMapping("/reports")
    public ResponseEntity<?> generateLifecycleReports(@RequestParam List<Integer> relatedIds) {
        try {
            List<LifecycleEvent> reports = lifecycleeventService.generateLifecycleReports(relatedIds);
            return ResponseEntity.ok(reports);
        } catch (LifecycleEventNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lifecycle events not found for provided asset IDs: " + relatedIds);
        }
    }
 

	
	
	
}