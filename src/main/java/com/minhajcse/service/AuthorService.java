package com.minhajcse.service;

import com.minhajcse.exception.AuthorNotFoundException;
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

    public Author getAuthorById(Long id) throws AuthorNotFoundException {
        return authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException("Author with id " + id + " not found")
        );
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public Long createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public void updateAuthor(Author author) {
        authorRepository.update(author);
    }

    public boolean existsAuthor(Long id){
        return authorRepository.existsById(id);
    }
}
