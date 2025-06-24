package com.ehs.booklending.repository;

import com.ehs.booklending.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * BookRepository is a repository interface for managing Book entities.
 * Extends JpaRepository to provide basic CRUD functionalities and query methods
 * for Book entities identified by a UUID.
 *
 * This interface also includes a custom query method to retrieve books borrowed
 * by a specific user.
 */
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByBorrowedByUser(UUID userId);
}