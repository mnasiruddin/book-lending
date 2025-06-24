package com.ehs.booklending.controller;

import com.ehs.booklending.domain.BookDTO;
import com.ehs.booklending.entity.Book;
import com.ehs.booklending.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * BookController is a REST controller that provides endpoints for managing book-related
 * operations, such as retrieving all books, reserving a book, returning a book, and fetching
 * comprehensive book details.
 *
 * This controller handles HTTP requests related to the Book entity and delegates the
 * corresponding business logic to the BookService.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok()  .body(bookService.findAll());
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<Book> reserve(@PathVariable UUID id, @RequestParam UUID userId) {
        Optional<Book> reserved = bookService.reserve(id, userId);
        return reserved
                .map(book -> ResponseEntity.status(HttpStatus.CREATED).body(book))
                .orElseThrow(() -> new NoSuchElementException("Book not found to reserve"));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable UUID id) {
        Optional<Book> returned = bookService.returnBook(id);
        return returned
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Book not found to return"));
    }

    @GetMapping("/borrowed")
    public List<Book> getBorrowedBy(@RequestParam UUID userId) {
        return bookService.findBorrowedBy(userId);
    }

    @GetMapping("/extended")
    public List<BookDTO> getAllWithBorrowerInfo() {
        return bookService.findAllWithBorrowerInfo();
    }

}