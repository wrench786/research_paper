package com.minhajcse.service;

import com.minhajcse.model.Author;
import com.minhajcse.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        try {
            return authorRepository.save(author);
        }
        catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteAuthor(Long id) throws Exception {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new Exception("Author with id " + id + " not found");
        }
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }
}
