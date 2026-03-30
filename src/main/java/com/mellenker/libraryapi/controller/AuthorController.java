package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Author geAuthorById(@PathVariable long id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/authors")
    public void addAuthor(@RequestBody Author author) {
        service.addAuthor(author);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable long id) {
        service.deleteAuthor(id);
    }

    @PutMapping("/authors")
    public void updateAuthor(@RequestBody Author author) {
        service.updateAuthor(author);
    }

    @PatchMapping("/authors/{id}")
    public void updateAuthorByFields(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        service.updateAuthorByFields(id, fields);
    }
}
