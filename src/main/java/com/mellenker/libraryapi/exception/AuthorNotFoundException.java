package com.mellenker.libraryapi.exception;

public class AuthorNotFoundException extends ResourceNotFoundException {
    public AuthorNotFoundException(Long id) {
        super("Author not found with id: " + id);
    }
}