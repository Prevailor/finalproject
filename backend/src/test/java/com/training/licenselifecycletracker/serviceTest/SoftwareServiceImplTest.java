package com.training.licenselifecycletracker.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.licenselifecycletracker.entities.Software;
import com.training.licenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.licenselifecycletracker.repositories.SoftwareRepository;
import com.training.licenselifecycletracker.service.SoftwareServiceImpl;

public class SoftwareServiceImplTest {

    @Mock
    private SoftwareRepository repo;

    @InjectMocks
    private SoftwareServiceImpl softwareService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSoftware_Success() {
        Software software = new Software();
        when(repo.save(software)).thenReturn(software);

        Software result = softwareService.addSoftware(software);

        assertEquals(software, result);
    }

    @Test
    public void testViewSoftwares_Success() throws SoftwareNotFoundException {
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());

        // Mock the repository behavior
        when(repo.findAll()).thenReturn(softwares);

        // Call the service method
        ResponseEntity<List<Software>> responseEntity = softwareService.viewSoftwares();

        // Assert the response entity status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the response entity body
        List<Software> result = responseEntity.getBody();
        assertEquals(softwares, result);
    }

    @Test
    public void testViewSoftwares_SoftwareNotFound() {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.viewSoftwares();
        });
    }

    @Test
    public void testGetSoftwareById_Success() throws SoftwareNotFoundException {
        Software software = new Software();
        when(repo.findById(1)).thenReturn(Optional.of(software));

        Software result = softwareService.getSoftwareById(1);

        assertEquals(software, result);
    }

    @Test
    public void testGetSoftwareById_SoftwareNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareById(1);
        });
    }

    @Test
    public void testGetSoftwareByName_Success() throws SoftwareNotFoundException {
        Software software = new Software();
        when(repo.findBySoftwareName("TestSoftware")).thenReturn(software);

        Software result = softwareService.getSoftwareByName("TestSoftware");

        assertEquals(software, result);
    }

    @Test
    public void testGetSoftwareByName_SoftwareNotFound() {
        when(repo.findBySoftwareName("TestSoftware")).thenReturn(null);

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareByName("TestSoftware");
        });
    }

    @Test
    public void testGetSoftwareByStatus_Success() throws SoftwareNotFoundException {
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        when(repo.findByStatus("Active")).thenReturn(softwares);

        List<Software> result = softwareService.getSoftwareByStatus("Active");

        assertEquals(softwares, result);
    }

    @Test
    public void testGetSoftwareByStatus_SoftwareNotFound() {
        when(repo.findByStatus("Active")).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareByStatus("Active");
        });
    }

    @Test
    public void testUpdateSoftware_Success() throws SoftwareNotFoundException {
        // Create a software object with a valid softwareId
        Software software = new Software();
        software.setSoftwareId(1);

        // Mock the repository behavior
        when(repo.findById(1)).thenReturn(Optional.of(software));
        when(repo.save(software)).thenReturn(software);

        // Call the service method
        Software result = softwareService.updateSoftware(software);

        // Assert the result
        assertEquals(software, result);
    }


    @Test
    public void testUpdateSoftware_SoftwareNotFound() {
        Software software = new Software();
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.updateSoftware(software);
        });
    }

    @Test
    public void testDeleteSoftwareById_Success() throws SoftwareNotFoundException {
        Software software = new Software();
        when(repo.findById(1)).thenReturn(Optional.of(software));

        String result = softwareService.deleteSoftwareById(1);

        assertEquals("Software with Id 1 deleted successfully", result);
    }

    @Test
    public void testDeleteSoftwareById_SoftwareNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.deleteSoftwareById(1);
        });
    }

    @Test
    public void testGetSoftwareByLicenseKey_Success() throws SoftwareNotFoundException {
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        when(repo.findByLicenseKey("XYZ123")).thenReturn(softwares);

        List<Software> result = softwareService.getSoftwareByLicenseKey("XYZ123");

        assertEquals(softwares, result);
    }

    @Test
    public void testGetSoftwareByLicenseKey_SoftwareNotFound() {
        when(repo.findByLicenseKey("XYZ123")).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareByLicenseKey("XYZ123");
        });
    }

    @Test
    public void testGetSoftwareByPurchaseDate_Success() throws SoftwareNotFoundException {
        LocalDate purchaseDate = LocalDate.now();
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        when(repo.findByPurchaseDate(purchaseDate)).thenReturn(softwares);

        List<Software> result = softwareService.getSoftwareByPurchaseDate(purchaseDate);

        assertEquals(softwares, result);
    }

    @Test
    public void testGetSoftwareByPurchaseDate_SoftwareNotFound() {
        LocalDate purchaseDate = LocalDate.now();
        when(repo.findByPurchaseDate(purchaseDate)).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareByPurchaseDate(purchaseDate);
        });
    }

    @Test
    public void testGetSoftwareByExpirationDate_Success() throws SoftwareNotFoundException {
        LocalDate expirationDate = LocalDate.now();
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        when(repo.findByExpirationDate(expirationDate)).thenReturn(softwares);

        List<Software> result = softwareService.getSoftwareByExpirationDate(expirationDate);

        assertEquals(softwares, result);
    }

    @Test
    public void testGetSoftwareByExpirationDate_SoftwareNotFound() {
        LocalDate expirationDate = LocalDate.now();
        when(repo.findByExpirationDate(expirationDate)).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareByExpirationDate(expirationDate);
        });
    }

    @Test
    public void testGetSoftwareBySupportEndDate_Success() throws SoftwareNotFoundException {
        LocalDate supportEndDate = LocalDate.now();
        List<Software> softwares = new ArrayList<>();
        softwares.add(new Software());
        when(repo.findBySupportEndDate(supportEndDate)).thenReturn(softwares);

        List<Software> result = softwareService.getSoftwareBySupportEndDate(supportEndDate);

        assertEquals(softwares, result);
    }

    @Test
    public void testGetSoftwareBySupportEndDate_SoftwareNotFound() {
        LocalDate supportEndDate = LocalDate.now();
        when(repo.findBySupportEndDate(supportEndDate)).thenReturn(new ArrayList<>());

        assertThrows(SoftwareNotFoundException.class, () -> {
            softwareService.getSoftwareBySupportEndDate(supportEndDate);
        });
    }
}
