package com.ehs.booklending.service;

import com.ehs.booklending.entity.AppUser;
import com.ehs.booklending.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser_Success() {
        // Arrange
        AppUser appUser = new AppUser();
        UUID userId = UUID.randomUUID();
        appUser.setId(userId);

        when(userRepository.save(any(AppUser.class))).thenReturn(appUser);

        // Act
        AppUser savedUser = userService.save(appUser);

        // Assert
        assertEquals(userId, savedUser.getId());
        verify(userRepository, times(1)).save(appUser);
    }
}