package com.training.licenselifecycletracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.licenselifecycletracker.entities.Device;
import com.training.licenselifecycletracker.entities.Software;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public String generateDeviceReport(List<Device> devices) {
        // Generate a report for the list of devices
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Device Report:\n\n");
        for (Device device : devices) {
            reportBuilder.append("Device ID: ").append(device.getDeviceId()).append("\n");
            reportBuilder.append("Device Name: ").append(device.getDeviceName()).append("\n");
            reportBuilder.append("Device Type: ").append(device.getDeviceType()).append("\n");
            reportBuilder.append("Purchase Date: ").append(device.getPurchaseDate()).append("\n");
            reportBuilder.append("Expiration Date: ").append(device.getExpirationDate()).append("\n");
            reportBuilder.append("End of Support Date: ").append(device.getEndOfSupportDate()).append("\n");
            reportBuilder.append("Status: ").append(device.getStatus()).append("\n\n");
        }
        return reportBuilder.toString();
    }

    @Override
    public String generateSoftwareReport(List<Software> softwareList) {
        // Generate a report for the list of software
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Software Report:\n\n");
        for (Software software : softwareList) {
            reportBuilder.append("Software ID: ").append(software.getSoftwareId()).append("\n");
            reportBuilder.append("Software Name: ").append(software.getSoftwareName()).append("\n");
            reportBuilder.append("License Key: ").append(software.getLicenseKey()).append("\n");
            reportBuilder.append("Purchase Date: ").append(software.getPurchaseDate()).append("\n");
            reportBuilder.append("Expiry Date: ").append(software.getExpirationDate()).append("\n");
            reportBuilder.append("Support End Date: ").append(software.getSupportEndDate()).append("\n");
            reportBuilder.append("Status: ").append(software.getStatus()).append("\n\n");
        }
        return reportBuilder.toString();
    }
}