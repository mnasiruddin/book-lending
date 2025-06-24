package com.ehs.booklending.service;

import com.ehs.booklending.domain.BookDTO;
import com.ehs.booklending.entity.Book;
import com.ehs.booklending.repository.BookRepository;
import com.ehs.booklending.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Service class responsible for managing book-related operations.
 * Provides methods to handle book retrieval, reservation, return,
 * and detailed book listing with borrower information.
 */
@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepo;

    public BookService(BookRepository bookRepository, UserRepository userRepo) {
        this.bookRepository = bookRepository;
        this.userRepo = userRepo;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findBorrowedBy(UUID userId) {

        List<Book> byBorrowedByUser = bookRepository.findByBorrowedByUser(userId);
        if (byBorrowedByUser.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        return byBorrowedByUser;
    }

    public Optional<Book> reserve(UUID bookId, UUID userId) {
        return bookRepository.findById(bookId)
                .map(book -> {
            if (book.isAvailable()) {
                book.setAvailable(false);
                book.setBorrowedByUser(userId);
                log.info("Book {} reserved to {}", bookId, userId);
                return bookRepository.save(book);
            } else {
                throw new IllegalArgumentException("Book is already borrowed");
            }
        });
    }

    public Optional<Book> returnBook(UUID bookId) {
        return bookRepository.findById(bookId).map(book -> {
            book.setAvailable(true);
            book.setBorrowedByUser(null);
            log.info("Book {} returned", bookId);
            return bookRepository.save(book);
        });
    }

    public List<BookDTO> findAllWithBorrowerInfo() {
        return bookRepository.findAll().stream().map(book -> {
            AtomicReference<String> username = new AtomicReference<>();
            if (book.getBorrowedByUser() != null) {
                userRepo.findById(book.getBorrowedByUser()).ifPresent(user -> {
                    username.set(user.getUsername());
                });
            }
            return new BookDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.isAvailable(),
                    book.getBorrowedByUser(),
                    username.get()
            );
        }).toList();
    }

}