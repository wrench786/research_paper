package com.minhajcse.controller;

import com.minhajcse.model.Paper;
import com.minhajcse.service.PaperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papers")

public class PaperController {
    private final PaperService paperService;
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
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

    @PostMapping
    public ResponseEntity<Paper> createPaper(@RequestBody Paper paper) {
        Paper createdPaper = paperService.createPaper(paper);
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

    @GetMapping
    public ResponseEntity<List<Paper>> getAllPapers() {
        return ResponseEntity.ok(paperService.getAllPapers());
    }
}
