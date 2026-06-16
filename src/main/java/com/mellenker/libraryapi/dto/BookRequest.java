package com.mellenker.libraryapi.dto;

import lombok.Data;

import java.util.List;

import jakarta.validation.constraints.NotNull;

@Data
public class BookRequest {
    @NotNull(message = "Title cannot be null")
    private String title;
    private String isbn;
    private Integer publicationYear;
    private String description;
    private String coverImageUrl;
    @NotNull(message = "Available Copies cannot be null")
    private Integer availableCopies;
    @NotNull(message = "AuthorIds list cannot be empty")
    private List<Long> authorIds;
}
