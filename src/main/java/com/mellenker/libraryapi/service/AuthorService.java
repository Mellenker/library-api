package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
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
        return repo.findById(id).orElse(null);
    }

    public void addAuthor(Author author) {
        repo.save(author);
    }

    public void updateAuthor(Author author) {
        repo.save(author);
    }

    public void deleteAuthor(long id) {
        repo.deleteById(id);
    }

    public void updateAuthorByFields(Long id, Map<String, Object> fields) {
        Author author = repo.findById(id).orElse(null);

        if (fields.containsKey("name")) {
            author.setName((String) fields.get("name"));
        }
        if (fields.containsKey("birthYear")) {
            author.setBirthYear((Integer) fields.get("birthYear"));
        }
        if (fields.containsKey("bio")) {
            author.setBio((String) fields.get("bio"));
        }

        repo.save(author);
    }


}
