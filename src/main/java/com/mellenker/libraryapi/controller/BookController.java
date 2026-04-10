package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<BookResponse> getBooks() {
        return service.getBooks();
    }
}
