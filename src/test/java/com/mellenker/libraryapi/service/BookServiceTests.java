package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.BookRequest;
import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookUpdateRequest;
import com.mellenker.libraryapi.exception.BookNotFoundException;
import com.mellenker.libraryapi.mapper.BookMapper;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.model.Book;
import com.mellenker.libraryapi.repository.AuthorRepo;
import com.mellenker.libraryapi.repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    BookRepo bookRepo;
    @Mock
    AuthorRepo authorRepo;
    @Mock
    BookMapper mapper;
    @InjectMocks
    BookService service;

    @Test
    void addBook_shouldSetAuthorsAndReturnResponse_whenValidRequestProvided() {
        BookRequest request = new BookRequest();
        request.setTitle("Crime and Punishment");
        request.setAuthorIds(List.of(1L));

        Book book = new Book();
        book.setTitle("Crime and Punishment");

        Author author = new Author();
        author.setId(1L);
        author.setName("Fyodor Dostoevsky");

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Crime and Punishment");
        savedBook.setAuthors(List.of(author));

        BookResponse response = new BookResponse();
        response.setId(1L);
        response.setTitle("Crime and Punishment");

        when(mapper.toEntity(request)).thenReturn(book);
        when(authorRepo.findAllById(request.getAuthorIds())).thenReturn(List.of(author));
        when(bookRepo.save(book)).thenReturn(savedBook);
        when(mapper.toResponse(savedBook)).thenReturn(response);

        BookResponse result = service.addBook(request);

        assertEquals(response.getId(), result.getId());
        assertEquals(response.getTitle(), result.getTitle());
        verify(bookRepo).save(book);
        verify(authorRepo).findAllById(request.getAuthorIds());
    }

    @Test
    void getBookById_shouldThrowBookNotFoundException_whenBookNotFound() {
        long id = 1L;
        when(bookRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> service.getBookById(id));
    }

    @Test
    void getBookById_shouldReturnBookResponse_whenBookExists() {
        long id = 1L;
        Book book = new Book();
        book.setId(id);
        BookResponse response = new BookResponse();
        response.setId(id);

        when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        when(mapper.toResponse(book)).thenReturn(response);

        BookResponse result = service.getBookById(id);

        assertEquals(response.getId(), result.getId());
        verify(mapper).toResponse(book);
    }

    @Test
    void updateBook_shouldThrowBookNotFoundException_whenBookNotFound() {
        long id = 1L;
        when(bookRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> service.updateBook(id, new BookUpdateRequest()));
    }

    @Test
    void updateBook_shouldOnlyUpdateNonNullFields_whenPartialRequestProvided() {
        long id = 1L;

        BookUpdateRequest request = new BookUpdateRequest();
        request.setTitle("Updated Title");
        request.setAvailableCopies(10);

        Book book = new Book();
        book.setId(id);
        book.setTitle("Original Title");
        book.setIsbn("1234567890");
        book.setAvailableCopies(5);

        when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        when(bookRepo.save(book)).thenReturn(book);

        service.updateBook(id, request);

        assertEquals("Updated Title", book.getTitle());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(10, book.getAvailableCopies());
        verify(bookRepo).save(book);
    }
}
