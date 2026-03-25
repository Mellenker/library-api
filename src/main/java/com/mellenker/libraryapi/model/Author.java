package com.mellenker.libraryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private long id;
    private String name;
    private int birthYear;
    private String bio;

}
