package com.mellenker.libraryapi.controller;

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
    @Autowired
    AuthorService service;

    @RequestMapping("/authors")
    public List<Author> getAuthors() {
        return service.getAuthors();
    }

    @RequestMapping("/authors/{id}")
    public ResponseEntity<Author> geAuthorById(@PathVariable long id) {
        var author = service.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Long id = service.addAuthor(author);
        return ResponseEntity.created(URI.create("/authors/" + id)).body(author);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/authors")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        var updatedAuthor = service.updateAuthor(author);

        if (updatedAuthor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAuthor);
    }

    @PatchMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthorByFields(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        var updatedAuthor = service.updateAuthorByFields(id, fields);

        if (updatedAuthor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAuthor);
    }
}
