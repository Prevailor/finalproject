package com.training.licenselifecycletracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.entities.LifecycleEvent;
import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;

public interface LifecycleeventService {
	
	//Create
		public LifecycleEvent addLifeCycleEvent(LifecycleEvent lifecycleevent);
		//Retrieve
		public LifecycleEvent getLifeCycleEventById(Integer eventId) throws LifecycleEventNotFoundException;
		
		public List<LifecycleEvent> getLifeCycleEventByEventType(String eventType)throws LifecycleEventNotFoundException;
		
	
		public List<LifecycleEvent> getLifeCycleEventByEventDate(LocalDate eventDate)throws LifecycleEventNotFoundException;
		
		public List<LifecycleEvent> getLifeCycleEventByDescription(String description)throws LifecycleEventNotFoundException;
		
		public List<LifecycleEvent> getLifeCycleEventByCategory(String category)throws LifecycleEventNotFoundException;
		
		public ResponseEntity<List<LifecycleEvent>> viewLifecycleEvents()throws LifecycleEventNotFoundException;
				
		//Delete
		public String deleteLifeCycleEventById(Integer eventId)throws LifecycleEventNotFoundException;
		
		//Update
		public LifecycleEvent updateLifeCycleEvent(LifecycleEvent lifecycleevent)throws LifecycleEventNotFoundException;


		
		public ResponseEntity<List<LifecycleEvent>> viewLifecycleEvent()throws LifecycleEventNotFoundException;


		public List<LifecycleEvent> generateLifecycleReports(List<Integer> relatedIds)throws LifecycleEventNotFoundException;
		
		
}
