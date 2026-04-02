package com.mellenker.libraryapi.dto;

import lombok.Data;

@Data
public class AuthorRequest {
    private String name;
    private String bio;
    private Integer birthYear;
}
