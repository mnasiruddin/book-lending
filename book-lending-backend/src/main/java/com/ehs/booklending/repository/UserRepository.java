package com.ehs.booklending.repository;

import com.ehs.booklending.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * UserRepository is a repository interface for managing AppUser entities.
 * It extends JpaRepository, providing standard CRUD operations and query
 * methods for AppUser entities identified by a UUID.
 *
 * This interface serves as the data access layer for AppUser entities,
 * allowing interaction with the database for user-related operations.
 */
public interface UserRepository extends JpaRepository<AppUser, UUID> {
}