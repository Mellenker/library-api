package com.mellenker.libraryapi.dto;

import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String bio;
    private Integer birthYear;
}
