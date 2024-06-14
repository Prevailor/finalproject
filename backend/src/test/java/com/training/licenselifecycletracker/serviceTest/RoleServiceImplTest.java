package com.training.licenselifecycletracker.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations; // Import MockitoAnnotations
import org.junit.jupiter.api.BeforeEach; // Import BeforeEach

import com.training.licenselifecycletracker.entities.ERole;
import com.training.licenselifecycletracker.entities.Role;
import com.training.licenselifecycletracker.repositories.RoleRepository;
import com.training.licenselifecycletracker.service.RoleServiceImpl;

public class RoleServiceImplTest {

    @Mock
    private RoleRepository repo;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach // Add this annotation to initialize Mockito annotations before each test
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindRoleByName_Success() {
        // Mock the repository behavior
        ERole roleName = ERole.ROLE_USER;
        Role role = new Role(1, roleName);
        when(repo.findByName(roleName)).thenReturn(Optional.of(role));

        // Call the service method
        Optional<Role> result = roleService.findRoleByName(roleName);

        // Assert the result
        assertEquals(Optional.of(role), result);
    }
    
    @Test
    public void testFindRoleByName_RoleNotFound() {
        // Mock the repository behavior to return empty optional
        ERole roleName = ERole.ROLE_USER;
        when(repo.findByName(roleName)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Role> result = roleService.findRoleByName(roleName);

        // Assert that result is empty optional
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindRoleById_Success() {
        // Mock the repository behavior
        Role role = new Role(1, ERole.ROLE_USER);
        when(repo.findById(1)).thenReturn(Optional.of(role));

        // Call the service method
        Optional<Role> result = roleService.findRoleById(1);

        // Assert the result
        assertEquals(Optional.of(role), result);
    }


    @Test
    public void testFindRoleById_RoleNotFound() {
        // Mock the repository behavior to return empty optional
        when(repo.findById(1)).thenReturn(Optional.empty());

        // Call the service method
        Optional<Role> result = roleService.findRoleById(1);

        // Assert that result is empty optional
        assertTrue(result.isEmpty());
    }
}
