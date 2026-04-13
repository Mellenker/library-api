package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.BookRequest;
import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookUpdateRequest;
import com.mellenker.libraryapi.exception.BookNotFoundException;
import com.mellenker.libraryapi.mapper.BookMapper;
import com.mellenker.libraryapi.model.Book;
import com.mellenker.libraryapi.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo repo;
    private final BookMapper mapper;

    @Autowired
    public BookService(BookRepo repo, BookMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<BookResponse> getBooks() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public BookResponse getBookById(long id) {
        Book book = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return mapper.toResponse(book);
    }

    public BookResponse addBook(BookRequest request) {
        System.out.println("BEFORE:" + request.getTitle());
        Book book = mapper.toEntity(request);
        System.out.println("AFTER:" + book.getTitle());
        return mapper.toResponse(repo.save(book));
    }

    public void deleteBook(long id) {
        repo.deleteById(id);
    }

    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }
        if (request.getPublicationYear() != null) {
            book.setPublicationYear(request.getPublicationYear());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        if (request.getCoverImageUrl() != null) {
            book.setCoverImageUrl(request.getCoverImageUrl());
        }
        if (request.getAvailableCopies() != null) {
            book.setAvailableCopies(request.getAvailableCopies());
        }

        return mapper.toResponse(repo.save(book));
    }
}
