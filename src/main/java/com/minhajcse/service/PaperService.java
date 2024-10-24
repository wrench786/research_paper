package com.minhajcse.service;

import com.minhajcse.model.Paper;
import com.minhajcse.repository.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {
    private final PaperRepository paperRepository;

    public PaperService(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
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
