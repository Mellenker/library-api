package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.dto.AuthorUpdateRequest;
import com.mellenker.libraryapi.exception.AuthorNotFoundException;
import com.mellenker.libraryapi.mapper.AuthorMapper;
import com.mellenker.libraryapi.entity.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {
    @Mock
    AuthorRepo repo;
    @Mock
    AuthorMapper mapper;
    @InjectMocks
    AuthorService service;

    @Test
    void addAuthor_shouldReturnAuthorResponse_whenValidRequestProvided() {
        AuthorRequest request = new AuthorRequest();
        request.setName("Fyodor Dostoevsky");
        request.setBirthYear(1821);
        request.setBio("Fyodor Dostoevsky was a Russian writer, poet, and philosopher.");

        Author author = new Author();
        author.setId(1L);
        author.setName("Fyodor Dostoevsky");
        author.setBirthYear(1821);
        author.setBio("Fyodor Dostoevsky was a Russian writer, poet, and philosopher.");

        AuthorResponse response = new AuthorResponse();
        response.setId(1L);
        response.setName("Fyodor Dostoevsky");
        response.setBirthYear(1821);
        response.setBio("Fyodor Dostoevsky was a Russian writer, poet, and philosopher.");

        when(mapper.toEntity(request)).thenReturn(author);
        when(repo.save(author)).thenReturn(author);
        when(mapper.toResponse(author)).thenReturn(response);

        var result = service.addAuthor(request);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());
        assertEquals(author.getBio(), result.getBio());
        verify(repo).save(author);
    }

    @Test
    void getAuthorById_shouldThrowAuthorNotFoundException_whenAuthorNotFound() {
        long id = 1L;
        when(repo.findById(id)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> service.getAuthorById(id));
    }

    @Test
    void getAuthorById_shouldReturnAuthorResponse_whenAuthorExists() {
        long id = 1L;
        Author author = new Author();
        author.setId(id);
        AuthorResponse response = new AuthorResponse();
        response.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(author));
        when(mapper.toResponse(author)).thenReturn(response);

        AuthorResponse result = service.getAuthorById(id);

        assertEquals(response.getId(), result.getId());
        verify(mapper).toResponse(author);
    }

    @Test
    void updateAuthorByFields_shouldThrowAuthorNotFoundException_whenAuthorNotFound() {
        long id = 1L;
        when(repo.findById(id)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> service.updateAuthor(id, null));
    }

    @Test
    void updateAuthorByFields_shouldOnlyUpdateNonNullFields_whenPartialRequestProvided() {
        long id = 1L;

        AuthorUpdateRequest request = new AuthorUpdateRequest();
        request.setBirthYear(200);
        request.setBio("Hello World");

        Author author = new Author();
        author.setId(id);
        author.setName("Fyodor Dostoevsky");
        author.setBirthYear(1821);
        author.setBio("Fyodor Dostoevsky was a Russian writer, poet, and philosopher.");

        when(repo.findById(id)).thenReturn(Optional.of(author));
        when(repo.save(author)).thenReturn(author);

        service.updateAuthor(id, request);

        assertEquals("Fyodor Dostoevsky", author.getName());
        assertEquals(request.getBirthYear(), author.getBirthYear());
        assertEquals(request.getBio(), author.getBio());
        verify(repo).save(author);
    }
}
