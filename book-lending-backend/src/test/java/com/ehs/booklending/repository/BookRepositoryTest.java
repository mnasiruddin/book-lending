package com.ehs.booklending.repository;

import com.ehs.booklending.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Mock
    private BookRepository mockBookRepository;

    public BookRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Unit tests for the findByBorrowedByUser method in BookRepository.
     * This method retrieves all books that are borrowed by a specific user based on their ID.
     */

    @Test
    void testFindByBorrowedByUser_Found() {
        // Arrange
        UUID userId = UUID.randomUUID();

        Book book1 = new Book();
        book1.setId(UUID.randomUUID());
        book1.setBorrowedByUser(userId);

        Book book2 = new Book();
        book2.setId(UUID.randomUUID());
        book2.setBorrowedByUser(userId);

        when(mockBookRepository.findByBorrowedByUser(userId)).thenReturn(List.of(book1, book2));

        // Act
        List<Book> borrowedBooks = mockBookRepository.findByBorrowedByUser(userId);

        // Assert
        assertThat(borrowedBooks).isNotNull();
        assertThat(borrowedBooks.size()).isEqualTo(2);
        assertThat(borrowedBooks).containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void testFindByBorrowedByUser_NotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(mockBookRepository.findByBorrowedByUser(userId)).thenReturn(List.of());

        // Act
        List<Book> borrowedBooks = mockBookRepository.findByBorrowedByUser(userId);

        // Assert
        assertThat(borrowedBooks).isNotNull();
        assertThat(borrowedBooks).isEmpty();
    }

    @Test
    void testFindByBorrowedByUser_NullUserId() {
        // Arrange
        when(mockBookRepository.findByBorrowedByUser(null)).thenReturn(List.of());

        // Act
        List<Book> borrowedBooks = mockBookRepository.findByBorrowedByUser(null);

        // Assert
        assertThat(borrowedBooks).isNotNull();
        assertThat(borrowedBooks).isEmpty();
    }
}