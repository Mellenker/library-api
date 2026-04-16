package com.mellenker.libraryapi.mapper;

import com.mellenker.libraryapi.dto.AuthorRequest;
import com.mellenker.libraryapi.dto.AuthorResponse;
import com.mellenker.libraryapi.dto.AuthorSummary;
import com.mellenker.libraryapi.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequest request);

    AuthorResponse toResponse(Author author);

    AuthorSummary toSummary(Author author);
}
