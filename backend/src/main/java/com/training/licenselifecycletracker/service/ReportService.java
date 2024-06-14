package com.training.licenselifecycletracker.service;

import java.util.List;

import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.entities.Software;

public interface ReportService {
	
	
	    String generateDeviceReport(List<Device> devices);
	    String generateSoftwareReport(List<Software> softwareList);
	

}
