package com.mellenker.libraryapi.dto;

import lombok.Data;

@Data
public class AuthorUpdateRequest {
    private String name;
    private String bio;
    private Integer birthYear;
}
