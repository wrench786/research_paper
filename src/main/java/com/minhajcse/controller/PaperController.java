package com.minhajcse.controller;

import com.minhajcse.model.Paper;
import com.minhajcse.repository.PaperAndAuthorRepository;
import com.minhajcse.service.PaperAndAuthorService;
import com.minhajcse.service.PaperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papers")

public class PaperController {
    private final PaperService paperService;
    private final PaperAndAuthorService paperAndAuthorService;
    public PaperController(PaperService paperService, PaperAndAuthorService paperAndAuthorService) {
        this.paperService = paperService;
        this.paperAndAuthorService = paperAndAuthorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paper> getPaper(@PathVariable Long id) {
        Paper paper = paperService.getPaperById(id);
        if (paper != null) {
            return ResponseEntity.ok(paper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Paper> createPaper(@RequestBody Paper paper, List<Long> authorList) {
        Paper createdPaper = paperService.createPaper(paper);
        for(Long authorId: authorList){
            try {
                paperAndAuthorService.addAuthorToPaper(paper.getPaperId(), authorId);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paper> updatePaper(@PathVariable Long id, @RequestBody Paper paper) {
        paper.setPaperId(id);
        Paper updatedPaper = paperService.updatePaper(paper);
        return ResponseEntity.ok(updatedPaper);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaper(@PathVariable Long id) {
        paperService.deletePaper(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Paper>> getAllPapers() {
        return ResponseEntity.ok(paperService.getAllPapers());
    }
}
