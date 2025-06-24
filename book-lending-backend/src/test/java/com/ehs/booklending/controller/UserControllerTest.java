package com.ehs.booklending.controller;

import com.ehs.booklending.entity.AppUser;
import com.ehs.booklending.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * Tests the successful retrieval of a user by their ID.
     */
    @Test
    void testGetUser_Success() throws Exception {
        UUID userId = UUID.randomUUID();
        AppUser mockUser = new AppUser();
        mockUser.setId(userId);

        Mockito.when(userService.findByUser(userId))
                .thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userId.toString())));
    }

    /**
     * Tests the failure when a user is not found by their ID.
     */
    @Test
    void testGetUser_UserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();

        Mockito.when(userService.findByUser(any(UUID.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}