package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.exception.AuthorNotFoundException;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    private final AuthorRepo repo;

    @Autowired
    public AuthorService(AuthorRepo repo) {
        this.repo = repo;
    }

    public List<AuthorResponse> getAuthors() {
        return repo.findAll().stream().map(this::mapToResponse).toList();
    }

    public AuthorResponse getAuthorById(long id) {
        Author author = repo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return mapToResponse(author);
    }

    public AuthorResponse addAuthor(AuthorRequest request) {
        Author author = mapToEntity(request);
        return mapToResponse(repo.save(author));
    }

    public AuthorResponse updateAuthor(Author author) {
        return mapToResponse(repo.save(author));
    }

    public void deleteAuthor(long id) {
        repo.deleteById(id);
    }

    public AuthorResponse updateAuthorByFields(Long id, Map<String, Object> fields) {
        var author = repo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        // Only update specified fields
        if (fields.containsKey("name")) {
            author.setName((String) fields.get("name"));
        }
        if (fields.containsKey("birthYear")) {
            author.setBirthYear((Integer) fields.get("birthYear"));
        }
        if (fields.containsKey("bio")) {
            author.setBio((String) fields.get("bio"));
        }

        return mapToResponse(repo.save(author));
    }

    private Author mapToEntity(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBio(request.getBio());
        author.setBirthYear(request.getBirthYear());
        return author;
    }

    private AuthorResponse mapToResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBio(author.getBio());
        response.setBirthYear(author.getBirthYear());
        return response;
    }


}
