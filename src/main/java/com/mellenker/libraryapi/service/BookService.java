package com.mellenker.libraryapi.service;

import com.mellenker.libraryapi.dto.BookResponse;
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
        List<Book> books = repo.findAll();
        System.out.println(books);
        List<BookResponse> hejma = repo.findAll().stream()
                .map(mapper::toResponse)
                .toList();
        System.out.println(hejma);
        return hejma;
    }
}
