package com.training.licenselifecycletracker.service;

import java.util.List;

import com.training.licenselifecycletracker.exceptions.LifecycleEventNotFoundException;

public interface TechnicalSupportService {
	public void logFault(Integer deviceId, String description, String date, String category)throws LifecycleEventNotFoundException;

	public void updateFaultLog(Integer deviceId, String repairDetails, String category, String eventType);

	List<String> viewEndOfSupportDates();


}