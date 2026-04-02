package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.model.Author;
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
    public ResponseEntity<AuthorResponse> geAuthorById(@PathVariable long id) {
        var author = service.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
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

    @PutMapping("/authors")
    public ResponseEntity<AuthorResponse> updateAuthor(@RequestBody Author author) {
        var response = service.updateAuthor(author);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> updateAuthorByFields(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        var response = service.updateAuthorByFields(id, fields);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
