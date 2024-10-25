package com.minhajcse.repository;

import com.minhajcse.model.Paper;

import java.util.List;
import java.util.Optional;

public interface PaperRepository {
    Optional<Paper> findById(Long id);
    List<Paper> findAll();
    Long save(Paper paper);
    void deleteById(Long id);
    void update(Paper paper);
    boolean existsById(Long id);
}
