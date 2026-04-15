package com.mellenker.libraryapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String bio;
    private Integer birthYear;
    private List<BookSummary> books;
}
