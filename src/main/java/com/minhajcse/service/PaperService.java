package com.minhajcse.service;

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


    public Paper getPaperById(Long id) {
        return paperRepository.findById(id).orElse(null);
    }

    public List<Paper> getAllPapers() {
        return (List<Paper>) paperRepository.findAll();
    }

    public Paper createPaper(Paper paper) {
        return paperRepository.save(paper);
    }

    public void deletePaper(Long id) {
        paperRepository.deleteById(id);
    }

    public Paper updatePaper(Paper paper) {
        return paperRepository.save(paper);
    }


}
