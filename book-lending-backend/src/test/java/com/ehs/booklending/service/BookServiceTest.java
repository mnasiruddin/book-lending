package com.ehs.booklending.service;

import com.ehs.booklending.entity.Book;
import com.ehs.booklending.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testReserve_WhenBookIsAvailable_ShouldReserveBook() {
        // Arrange
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(true);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Optional<Book> reservedBook = bookService.reserve(bookId, userId);

        // Assert
        assertTrue(reservedBook.isPresent());
        assertFalse(reservedBook.get().isAvailable());
        assertEquals(userId, reservedBook.get().getBorrowedByUser());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testReserve_WhenBookIsUnavailable_ShouldThrowIllegalArgumentException() {
        // Arrange
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Book book = new Book();
        book.setId(bookId);
        book.setAvailable(false);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.reserve(bookId, userId);
        });
        assertEquals("Book is already borrowed", exception.getMessage());
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testReserve_WhenBookDoesNotExist_ShouldReturnEmptyOptional() {
        // Arrange
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act
        Optional<Book> result = bookService.reserve(bookId, userId);

        // Assert
        assertTrue(result.isEmpty());
        verify(bookRepository, never()).save(any(Book.class));
    }
}