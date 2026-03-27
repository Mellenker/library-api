package com.mellenker.libraryapi.service;
import com.mellenker.libraryapi.model.Author;
import com.mellenker.libraryapi.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepo repo;

//    List<Author> authors = new ArrayList<>(Arrays.asList(
//            new Author(1, "Stephen King", 1947, "American author known for horror and supernatural fiction"),
//            new Author(2, "Jane Austen", 1817, "English author known for romantic fiction"),
//            new Author(3, "George Orwell", 1905, "English author known for science fiction")
//    ));

   public List<Author> getAuthors(){
        return repo.findAll();
   }

   public Author getAuthorById(long id){
       return repo.findById(id).orElse(null);
   }

   public void addAuthor(Author author) {
       repo.save(author);
   }

    public void updateAuthor(Author author) {
        repo.save(author);
    }

   public void deleteAuthor(long id) {
       repo.deleteById(id);
   }

}
