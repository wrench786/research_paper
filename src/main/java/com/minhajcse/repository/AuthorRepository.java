package com.minhajcse.repository;

import com.minhajcse.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository{
    Optional<Author> findById(Long id) ;
    List<Author> findAll();
    Long save(Author author);
    void update(Author author);
    void deleteById(Long id);
    boolean existsById(Long id);
}
