package com.minhajcse.service;

import com.minhajcse.exception.PaperNotFoundException;
import com.minhajcse.model.Author;
import com.minhajcse.model.Paper;
import com.minhajcse.model.PaperAndAuthor;
import com.minhajcse.repository.AuthorRepository;
import com.minhajcse.repository.PaperAndAuthorRepository;
import com.minhajcse.repository.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {
    private final PaperRepository paperRepository;
    private final AuthorRepository authorRepository;
    private final PaperAndAuthorRepository paperAndAuthorRepository;

    public PaperService(PaperRepository paperRepository, AuthorRepository authorRepository, PaperAndAuthorRepository paperAndAuthorRepository) {
        this.paperRepository = paperRepository;
        this.authorRepository = authorRepository;
        this.paperAndAuthorRepository = paperAndAuthorRepository;
    }


    public Paper getPaperById(Long id) throws PaperNotFoundException {
        return paperRepository.findById(id).orElseThrow(
                () -> new PaperNotFoundException("Paper not fournd for id" + id)
        );
    }

    public List<Paper> getAllPapers() {
        return (List<Paper>) paperRepository.findAll();
    }

    public Long createPaper(Paper paper) {
        return paperRepository.save(paper);
    }

    public void deletePaper(Long id) {
        paperRepository.deleteById(id);
    }

    public void updatePaper(Paper paper) {
        paperRepository.update(paper);
    }

    public boolean existsById(Long id) {
        return paperRepository.existsById(id);
    }
}
