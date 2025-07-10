package com.backend_user_service.backend.service;

import com.backend_user_service.backend.model.AppUser;
import com.backend_user_service.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void saveUser() {
        AppUser user = new AppUser();
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        AppUser savedUser = userService.saveUser(user);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertTrue(savedUser.isActive());
    }

    @Test
    void deleteUser() {
        AppUser user = new AppUser();
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.doNothing().when(userRepository).delete(user);

        String result = userService.deleteUser(user);
        assertEquals("User deleted successfully", result);
        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test
    void getUserById() {
        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<AppUser> retrievedUser = userService.getUserById("1");
        assertTrue(retrievedUser.isPresent());
        assertEquals(user.getUsername(), retrievedUser.get().getUsername());
        assertEquals(user.getEmail(), retrievedUser.get().getEmail());
        assertTrue(retrievedUser.get().isActive());
    }

    @Test
    void getAllUsers() {
        AppUser user1 = new AppUser();
        user1.setUsername("test_user1");
        user1.setEmail("test_user1@example.com");
        user1.setActive(true);

        AppUser user2 = new AppUser();
        user2.setUsername("test_user2");
        user2.setEmail("test_user2@example.com");
        user2.setActive(false);

        List<AppUser> users = List.of(user1, user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<AppUser> retrievedUsers = userService.getAllUsers();
        assertEquals(2, retrievedUsers.size());
        assertEquals(user1.getUsername(), retrievedUsers.get(0).getUsername());
        assertEquals(user2.getUsername(), retrievedUsers.get(1).getUsername());
    }


}