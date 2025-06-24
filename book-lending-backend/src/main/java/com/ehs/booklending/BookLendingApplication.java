package com.ehs.booklending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Book Lending Application.
 * This class serves as the main class for initializing and configuring the
 * Spring Boot application context, enabling the application to run.
 * The annotations and configuration settings defined here aid
 * in setting up the application as a Spring Boot application.
 */
@SpringBootApplication
public class BookLendingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookLendingApplication.class, args);
    }
}