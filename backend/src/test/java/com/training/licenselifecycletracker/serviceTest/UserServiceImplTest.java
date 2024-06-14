package com.training.licenselifecycletracker.serviceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.licenselifecycletracker.entities.ERole;
import com.training.licenselifecycletracker.entities.Role;
import com.training.licenselifecycletracker.entities.User;
import com.training.licenselifecycletracker.exceptions.UserNotFoundException;
import com.training.licenselifecycletracker.repositories.UserRepository;
import com.training.licenselifecycletracker.service.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        when(repo.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals(user, result);
        verify(repo, times(1)).save(user);
    }

    @Test
    public void testGetUserById_Success() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        when(repo.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertEquals(user, result);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1);
        });
    }

    @Test
    public void testDeleteUserById_Success() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        when(repo.findById(1)).thenReturn(Optional.of(user));

        String result = userService.deleteUserById(1);

        assertEquals("User with userId 1 deleted successfully", result);
        verify(repo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteUserById_UserNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUserById(1);
        });
    }

    @Test
    public void testUpdateUser_Success() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        when(repo.findById(1)).thenReturn(Optional.of(user));
        when(repo.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        User user = new User();
        user.setUserId(1);
        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(user);
        });
    }

    @Test
    public void testExistsByUsername() {
        String username = "test";
        when(repo.existsByUsername(username)).thenReturn(true);

        boolean result = userService.existsByUsername(username);

        assertTrue(result);
    }
    
 // Test for updateRole method when user exists
    @Test
    public void testUpdateRole_UserExists() throws UserNotFoundException {
        Integer userId = 1;
        User user = new User();
        user.setUserId(userId);
        Role role = new Role();
        role.setId(1); // Change to Integer
 
        when(repo.findById(userId)).thenReturn(Optional.of(user));
 
        String result = userService.updateRole(userId, role);
 
        verify(repo, times(1)).save(user);
        assertEquals("Role Updated Successfully!!!", result);
    }
 
    // Test for updateRole method when user does not exist
    @Test
    public void testUpdateRole_UserNotFound() {
        Integer userId = 1;
        Role role = new Role();
        role.setId(1); // Change to Integer
 
        when(repo.findById(userId)).thenReturn(Optional.empty());
 
        assertThrows(UserNotFoundException.class, () -> {
            userService.updateRole(userId, role);
        });
    }
    
 // Add similar test methods for addUserEntity, findByUsername, existsByUsername, findByRole
    // Test for addUserEntity method
    @Test
    public void testAddUserEntity() {
        User user = new User();
        user.setUserId(1); // Change to Long
 
        when(repo.save(user)).thenReturn(user);
 
        User result = userService.addUserEntity(user);
 
        verify(repo, times(1)).save(user);
        assertEquals(user, result);
    }
 
 // Test for findByUsername method when user exists
    @Test
    void testFindByUsername_UserExists() {
        // Given
        String username = "john_doe";
        User user = new User();
        when(repo.findByUsername(username)).thenReturn(user);

        // When
        Optional<User> result = userService.findByUsername(username);

        // Then
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }


    @Test
    void testFindByRole_UserExists() {
        // Given
        ERole role = ERole.ROLE_USER;
        User expectedUser = new User(/* provide user details */);
        Optional<User> user = Optional.of(expectedUser);
        when(repo.findByRole(role)).thenReturn(user);

        // When
        Optional<User> result = userService.findByRole(role);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get()); // Compare the contents of Optional with the expected User object
    }
    @Test
    void testFindByRole_UserNotExists() {
        // Given
        ERole role = ERole.ROLE_USER;
        when(repo.findByRole(role)).thenReturn(null);

        // When
        Optional<User> result = userService.findByRole(role);

        // Then
        assertFalse(result != null && result.isPresent());
    }

}
