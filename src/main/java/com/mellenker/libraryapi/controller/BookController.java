package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.AuthorSummary;
import com.mellenker.libraryapi.dto.BookRequest;
import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookSummary;
import com.mellenker.libraryapi.dto.BookUpdateRequest;
import com.mellenker.libraryapi.service.BookService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<BookSummary>> getBooks() {
        return ResponseEntity.ok(service.getBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable long id) {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @GetMapping("/books/{id}/authors")
    public ResponseEntity<List<AuthorSummary>> getAuthorsByBookId(@PathVariable long id) {
        return ResponseEntity.ok(service.getAuthorsByBookId(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> addBook(@RequestBody @Valid BookRequest request) {
        var response = service.addBook(request);
        return ResponseEntity.created(URI.create("/books/" + response.getId())).body(response);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable long id, @RequestBody @Valid BookUpdateRequest request) {
        var response = service.updateBook(id, request);
        return ResponseEntity.ok(response);
    }
}
