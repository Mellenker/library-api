package com.mellenker.libraryapi.service;
import com.mellenker.libraryapi.model.Author;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    List<Author> authors = Arrays.asList(
            new Author(1, "Stephen King", 1947, "American author known for horror and supernatural fiction"),
            new Author(2, "Jane Austen", 1817, "English author known for romantic fiction"),
            new Author(3, "George Orwell", 1905, "English author known for science fiction")
    );

   public List<Author> getAuthors(){
        return authors;
   }
}
