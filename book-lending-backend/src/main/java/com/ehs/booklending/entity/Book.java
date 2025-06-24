package com.ehs.booklending.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String author;
    private boolean available = true;

    @Column(name = "borrowed_by")
    private UUID borrowedByUser;
}