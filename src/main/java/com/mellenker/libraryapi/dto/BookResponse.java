package com.mellenker.libraryapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private Long id;

    private String title;
    private String isbn;
    private Integer publicationYear;
    private String description;
    private String coverImageUrl;
    private Integer availableCopies;

    private List<AuthorResponse> authors;
}
