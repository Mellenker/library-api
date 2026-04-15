package com.mellenker.libraryapi.mapper;

import com.mellenker.libraryapi.dto.BookRequest;
import com.mellenker.libraryapi.dto.BookResponse;
import com.mellenker.libraryapi.dto.BookSummary;
import com.mellenker.libraryapi.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {
    Book toEntity(BookRequest request);

    BookResponse toResponse(Book book);

    BookSummary toSummary(Book book);
}
