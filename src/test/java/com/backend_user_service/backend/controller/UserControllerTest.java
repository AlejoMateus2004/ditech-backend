package com.backend_user_service.backend.controller;

import com.backend_user_service.backend.model.AppUser;
import com.backend_user_service.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void saveUser() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        AppUser user = new AppUser();
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.when(userService.saveUser(Mockito.any(AppUser.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"test_user\", \"email\": \"test_user@example.com\", \"active\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("test_user"));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        AppUser user = new AppUser();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(Optional.of(user));

        Mockito.when(userService.deleteUser(Mockito.any(AppUser.class))).thenReturn("User deleted successfully");

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        AppUser user = new AppUser();
        user.setUsername("test_user");
        user.setEmail("test_user@example.com");
        user.setActive(true);

        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("test_user"));
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        AppUser user1 = new AppUser();
        user1.setUsername("test_user1");
        user1.setEmail("test_user1@example.com");
        user1.setActive(true);

        AppUser user2 = new AppUser();
        user2.setUsername("test_user2");
        user2.setEmail("test_user2@example.com");
        user2.setActive(true);

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("test_user1"))
                .andExpect(jsonPath("$[1].username").value("test_user2"));
    }

}