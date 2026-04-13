package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookUpdateRequest;
import com.mellenker.libraryapi.exception.BookNotFoundException;
import com.mellenker.libraryapi.mapper.BookMapper;
import com.mellenker.libraryapi.model.Book;
import com.mellenker.libraryapi.repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    BookRepo repo;
    @Mock
    BookMapper mapper;
    @InjectMocks
    BookService service;

    @Test
    void getBookById_shouldThrowBookNotFoundException_whenBookNotFound() {
        long id = 1L;
        when(repo.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> service.getBookById(id));
    }

    @Test
    void getBookById_shouldReturnBookResponse_whenBookExists() {
        long id = 1L;
        Book book = new Book();
        book.setId(id);
        BookResponse response = new BookResponse();
        response.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(book));
        when(mapper.toResponse(book)).thenReturn(response);

        BookResponse result = service.getBookById(id);

        assertEquals(response.getId(), result.getId());
        verify(mapper).toResponse(book);
    }

    @Test
    void updateBook_shouldThrowBookNotFoundException_whenBookNotFound() {
        long id = 1L;
        when(repo.findById(id)).thenReturn(Optional.empty());
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

        when(repo.findById(id)).thenReturn(Optional.of(book));
        when(repo.save(book)).thenReturn(book);

        service.updateBook(id, request);

        assertEquals("Updated Title", book.getTitle());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(10, book.getAvailableCopies());
        verify(repo).save(book);
    }
}
