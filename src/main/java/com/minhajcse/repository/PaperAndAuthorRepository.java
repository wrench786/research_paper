package com.minhajcse.repository;

import com.minhajcse.model.PaperAndAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperAndAuthorRepository extends CrudRepository<PaperAndAuthor, Long> {
    List<PaperAndAuthor> findAllByPaperId(Long paperId);
    List<PaperAndAuthor> findAllByAuthorId(Long authorId);
    PaperAndAuthor findByPaperIdAndAuthorId(Long paperId, Long authorId);
}
