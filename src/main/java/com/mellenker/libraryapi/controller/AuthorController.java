package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorController {
    AuthorService service;

    @Autowired
    AuthorController(AuthorService service) {
        this.service = service;
    }

    @RequestMapping("/authors")
    public List<AuthorResponse> getAuthors() {
        return service.getAuthors();
    }

    @RequestMapping("/authors/{id}")
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
    public ResponseEntity<AuthorResponse> updateAuthorByFields(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        var response = service.updateAuthorByFields(id, fields);
        return ResponseEntity.ok(response);
    }
}
