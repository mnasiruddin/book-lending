package com.ehs.booklending.controller;

import com.ehs.booklending.entity.Book;
import com.ehs.booklending.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    /**
     * Tests the 'reserve' method of the BookController class,
     * which handles the reservation of a book for a specific user.
     */

    @Test
    void testReserveBookSuccessfully() throws Exception {
        // Arrange
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Book book = new Book();
        book.setId(bookId);
        book.setBorrowedByUser(userId);
        when(bookService.reserve(bookId, userId)).thenReturn(Optional.of(book));

        // Act & Assert
        mockMvc.perform(post("/books/{id}/reserve", bookId)
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(bookId.toString()))
                .andExpect(jsonPath("$.borrowedByUser").value(userId.toString()));
    }

    @Test
    void testReserveBookNotFound() throws Exception {
        // Arrange
        UUID bookId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        when(bookService.reserve(bookId, userId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(post("/books/{id}/reserve", bookId)
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}