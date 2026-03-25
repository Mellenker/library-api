package com.mellenker.libraryapi.controller;

import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    AuthorService service;

    @RequestMapping("/authors")
    public List<Author> getAuthors(){
        return service.getAuthors();
    }
}
