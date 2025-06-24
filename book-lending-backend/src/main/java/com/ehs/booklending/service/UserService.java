package com.ehs.booklending.service;

import com.ehs.booklending.entity.AppUser;
import com.ehs.booklending.repository.BookRepository;
import com.ehs.booklending.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class responsible for handling user-related operations.
 * Provides methods to retrieve, save, and manage user entities in the application.
 */
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(BookRepository bookRepository, UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<AppUser> findAllUsers() {
        return userRepo.findAll();
    }

    public Optional<AppUser> findByUser(UUID userId) {
        return userRepo.findById(userId);
    }

    public AppUser save(AppUser appUser) {
        return userRepo.save(appUser);
    }

}