package com.training.licenselifecycletracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.exceptions.DeviceNotFoundException;

@Service
public interface DeviceService {

	//Create
	public Device addDevice(Device device);
	//Retrieve
	public Device getDeviceById(Integer deviceId)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByName(String deviceName)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByStatus(String status)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByDeviceType(String deviceType)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByPurchaseDate(LocalDate purchaseDate)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByExpirationDate(LocalDate expirationDate)throws DeviceNotFoundException;
	
	public List<Device> getDeviceByEndOfSupportDate(LocalDate endOfSupportDate)throws DeviceNotFoundException;
	
	public ResponseEntity<List<Device>> viewDevices()throws DeviceNotFoundException;
	
	//Delete
	public String deleteDeviceById(Integer deviceId)throws DeviceNotFoundException;
	
	//Update
	public Device updateDevice(Device device)throws DeviceNotFoundException;
	
	
	
	

}