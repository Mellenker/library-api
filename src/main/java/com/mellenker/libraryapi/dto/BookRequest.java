package com.mellenker.libraryapi.dto;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String isbn;
    private Integer publicationYear;
    private String description;
    private String coverImageUrl;
    private Integer availableCopies;
}
