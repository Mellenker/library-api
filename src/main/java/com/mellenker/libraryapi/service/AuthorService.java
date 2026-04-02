package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.exception.AuthorNotFoundException;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    @Autowired
    AuthorRepo repo;

    public List<Author> getAuthors() {
        return repo.findAll();
    }

    public Author getAuthorById(long id) {
        return repo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Long addAuthor(Author author) {
        return repo.save(author).getId();
    }

    public Author updateAuthor(Author author) {
        return repo.save(author);
    }

    public void deleteAuthor(long id) {
        repo.deleteById(id);
    }

    public Author updateAuthorByFields(Long id, Map<String, Object> fields) {
        var author = repo.findById(id).orElse(null);

        if (fields.containsKey("name")) {
            author.setName((String) fields.get("name"));
        }
        if (fields.containsKey("birthYear")) {
            author.setBirthYear((Integer) fields.get("birthYear"));
        }
        if (fields.containsKey("bio")) {
            author.setBio((String) fields.get("bio"));
        }

        return repo.save(author);
    }


}
