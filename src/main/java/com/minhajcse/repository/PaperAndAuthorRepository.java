package com.minhajcse.repository;

import com.minhajcse.model.PaperAndAuthor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PaperAndAuthorRepository{
    List<PaperAndAuthor> findAllByPaperId(Long paperId);
    List<PaperAndAuthor> findAllByAuthorId(Long authorId);
    PaperAndAuthor findByPaperIdAndAuthorId(Long paperId, Long authorId);
    Long save(PaperAndAuthor paperAndAuthor);
}
