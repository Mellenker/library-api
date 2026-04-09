package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.dto.AuthorUpdateRequest;
import com.mellenker.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService service;

    @Autowired
    AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/authors")
    public List<AuthorResponse> getAuthors() {
        return service.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable long id) {
        return ResponseEntity.ok(service.getAuthorById(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request) {
        var response = service.addAuthor(request);
        return ResponseEntity.created(URI.create("/authors/" + response.getId())).body(response);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> deleteAuthor(@PathVariable long id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable long id, @RequestBody AuthorUpdateRequest request) {
        var response = service.updateAuthor(id, request);
        return ResponseEntity.ok(response);
    }
}
