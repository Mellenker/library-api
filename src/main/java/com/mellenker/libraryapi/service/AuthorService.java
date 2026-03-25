package com.mellenker.libraryapi.service;
import com.mellenker.libraryapi.model.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    List<Author> authors = new ArrayList<>(Arrays.asList(
            new Author(1, "Stephen King", 1947, "American author known for horror and supernatural fiction"),
            new Author(2, "Jane Austen", 1817, "English author known for romantic fiction"),
            new Author(3, "George Orwell", 1905, "English author known for science fiction")
    ));

   public List<Author> getAuthors(){
        return authors;
   }

   public Author getAuthorById(long id){
        return authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
   }

   public void addAuthor(Author author) {
       authors.add(author);
   }

   public void deleteAuthor(long id) {
       authors.removeIf(author -> author.getId() == id);
   }

   public void updateAuthor(Author author) {
       authors.removeIf(author1 -> author1.getId() == author.getId());
       authors.add(author);
   }
}
