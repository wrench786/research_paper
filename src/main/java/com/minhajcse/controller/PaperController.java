package com.minhajcse.controller;

import com.minhajcse.dto.PaperDTO;
import com.minhajcse.exception.PaperNotFoundException;
import com.minhajcse.model.Paper;
import com.minhajcse.repository.PaperAndAuthorRepository;
import com.minhajcse.service.PaperAndAuthorService;
import com.minhajcse.service.PaperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paper")
public class PaperController {
    private final PaperService paperService;
    private final PaperAndAuthorService paperAndAuthorService;
    public PaperController(PaperService paperService, PaperAndAuthorService paperAndAuthorService) {
        this.paperService = paperService;
        this.paperAndAuthorService = paperAndAuthorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaper(@PathVariable Long id) {
        try{
            Paper paper = paperService.getPaperById(id);
            return ResponseEntity.ok(paper);
        }
        catch (PaperNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Paper> createPaper(@RequestBody PaperDTO paperDTO) {
        Paper paper = paperDTO.getPaper();
        paperService.createPaper(paper);

        List<Long> authorList = paperDTO.getAuthorList();
        for(Long authorId: authorList){
            try {
                paperAndAuthorService.addAuthorToPaper(paper.getPaperId(), authorId);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(paper);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Paper> updatePaper(@RequestBody Paper paper) {
        boolean flag = paperService.existsById(paper.getPaperId());
        if(flag){
            paperService.updatePaper(paper);
            return ResponseEntity.ok(paper);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaper(@PathVariable Long id) {
        boolean flag = paperService.existsById(id);
        if(flag){
            paperService.deletePaper(id);
            return ResponseEntity.ok("Deleted Paper");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Paper Id");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Paper>> getAllPapers() {
        return ResponseEntity.ok(paperService.getAllPapers());
    }
}
