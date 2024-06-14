package com.training.licenselifecycletracker.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.licenselifecycletracker.entities.LifecycleEvent;
@Repository
public interface LifecycleEventRepository extends CrudRepository<LifecycleEvent, Integer> {
	
	public List<LifecycleEvent> findByCategory(String category);
	
	public List<LifecycleEvent> findByEventType(String eventType);
	public List<LifecycleEvent> findByEventDate(LocalDate eventDate);
	public List<LifecycleEvent> findByDescription(String description);
	
	Optional<LifecycleEvent> findByRelatedIdAndEventType(Integer deviceId, String eventType);
	public List<LifecycleEvent> findAllByRelatedId(Integer relatedId);

	public LifecycleEvent findByRelatedId(Integer deviceId);
	
	
	
	
	 
}