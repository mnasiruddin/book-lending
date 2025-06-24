package com.ehs.booklending.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void handleNotFound_ShouldReturn404AndErrorMessage_WhenNoSuchElementExceptionThrown() throws Exception {
        mockMvc.perform(get("/non-existent-endpoint")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("javax.servlet.error.exception", new NoSuchElementException("Item not found")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("No resource found"))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}