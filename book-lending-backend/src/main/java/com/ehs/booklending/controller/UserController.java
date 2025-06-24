package com.ehs.booklending.controller;

import com.ehs.booklending.entity.AppUser;
import com.ehs.booklending.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * UserController is a REST controller that provides endpoints for managing
 * AppUser entities, including operations to create, retrieve, and list users.
 *
 * This controller handles HTTP requests related to users and delegates the
 * business logic to the UserService.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userService.findAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AppUser createUser(@RequestBody AppUser appUser) {
        return userService.save(appUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public AppUser getUser(@PathVariable UUID id) {
        return userService.findByUser(id)
                .orElseThrow(() -> new NoSuchElementException("user not found"));
    }


}
