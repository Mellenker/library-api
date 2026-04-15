package com.mellenker.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private String bio;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
