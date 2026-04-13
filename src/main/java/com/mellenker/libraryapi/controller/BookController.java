package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.BookRequest;
import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookUpdateRequest;
import com.mellenker.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable long id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) {
        var response = service.addBook(request);
        return ResponseEntity.created(URI.create("/books/" + response.getId())).body(response);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable long id, @RequestBody BookUpdateRequest request) {
        var response = service.updateBook(id, request);
        return ResponseEntity.ok(response);
    }
}
