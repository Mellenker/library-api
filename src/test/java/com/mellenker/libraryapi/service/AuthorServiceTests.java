package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.mapper.AuthorMapper;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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

        Author author = new Author();
        author.setId(1L);
        request.setName("Fyodor Dostoevsky");
        author.setBirthYear(1821);

        AuthorResponse response = new AuthorResponse();
        response.setId(1L);
        request.setName("Fyodor Dostoevsky");
        response.setBirthYear(1821);

        when(mapper.toEntity(request)).thenReturn(author);
        when(mapper.toResponse(author)).thenReturn(response);
        when(repo.save(author)).thenReturn(author);

        var result = service.addAuthor(request);

        assertEquals(author.getId(), result.getId());
        assertEquals(author.getName(), result.getName());
        assertEquals(author.getBio(), result.getBio());
        verify(repo).save(author);
    }
}
