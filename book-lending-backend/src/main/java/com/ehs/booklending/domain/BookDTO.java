package com.ehs.booklending.domain;

import java.util.UUID;

public record BookDTO(
        UUID id,
        String title,
        String author,
        boolean available,
        UUID borrowedById,
        String borrowedByUsername
) {}
