package com.training.licenselifecycletracker.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.entities.Software;
import com.training.licenselifecycletracker.exceptions.SoftwareNotFoundException;

public interface SoftwareService {
	
	//Create
		public Software addSoftware(Software software);
		//Retrieve
		public Software getSoftwareById(Integer softwareId) throws SoftwareNotFoundException;
		
		public Software getSoftwareByName(String softwareName)throws SoftwareNotFoundException;
		
		public List<Software> getSoftwareByStatus(String status)throws SoftwareNotFoundException;
		
		public List<Software> getSoftwareByLicenseKey(String licenseKey)throws SoftwareNotFoundException;
		
		public List<Software> getSoftwareByPurchaseDate(LocalDate purchaseDate)throws SoftwareNotFoundException;
		
		public List<Software> getSoftwareByExpirationDate(LocalDate expirationDate)throws SoftwareNotFoundException;
		
		public List<Software> getSoftwareBySupportEndDate(LocalDate supportEndDate)throws SoftwareNotFoundException;
		
		public ResponseEntity<List<Software>> viewSoftwares()throws SoftwareNotFoundException;

		
		//Delete
		public String deleteSoftwareById(Integer softwareId)throws SoftwareNotFoundException;
		
		//Update
		public Software updateSoftware(Software software)throws SoftwareNotFoundException;
		
		
		
	
		
}
