package com.mellenker.libraryapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorRequest {
    @NotNull(message = "Name cannot be null")
    private String name;
    private String bio;
    private Integer birthYear;
}
