package com.ehs.booklending.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String email;
}