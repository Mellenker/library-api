package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.dto.AuthorUpdateRequest;
import com.mellenker.libraryapi.exception.AuthorNotFoundException;
import com.mellenker.libraryapi.mapper.AuthorMapper;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepo repo;
    private final AuthorMapper mapper;

    @Autowired
    public AuthorService(AuthorRepo repo, AuthorMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<AuthorResponse> getAuthors() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    public AuthorResponse getAuthorById(long id) {
        Author author = repo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return mapper.toResponse(author);
    }

    public AuthorResponse addAuthor(AuthorRequest request) {
        Author author = mapper.toEntity(request);
        return mapper.toResponse(repo.save(author));
    }

    public void deleteAuthor(long id) {
        repo.deleteById(id);
    }

    public AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request) {
        Author author = repo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        // Only update specified fields
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getBirthYear() != null) {
            author.setBirthYear(request.getBirthYear());
        }
        if (request.getBio() != null) {
            author.setBio(request.getBio());
        }

        return mapper.toResponse(repo.save(author));
    }
}
