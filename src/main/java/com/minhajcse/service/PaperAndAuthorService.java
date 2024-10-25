package com.minhajcse.service;

import com.minhajcse.model.Author;
import com.minhajcse.model.Paper;
import com.minhajcse.model.PaperAndAuthor;
import com.minhajcse.repository.AuthorRepository;
import com.minhajcse.repository.PaperAndAuthorRepository;
import com.minhajcse.repository.PaperRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperAndAuthorService {
    private final PaperAndAuthorRepository paperAndAuthorRepository;
    private final AuthorRepository authorRepository;
    private final PaperRepository paperRepository;
    public PaperAndAuthorService(PaperAndAuthorRepository paperAndAuthorRepository, AuthorRepository authorRepository, PaperRepository paperRepository) {
        this.paperAndAuthorRepository = paperAndAuthorRepository;
        this.authorRepository = authorRepository;
        this.paperRepository = paperRepository;
    }

    public void addAuthorToPaper(Long paperId, Long authorId) throws Exception {
        Paper paper = paperRepository.findById(paperId).orElseThrow(
                () -> new Exception("Paper with id " + paperId + " does not exist")
        );
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new Exception("Author with id " + authorId + " does not exist")
        );
        PaperAndAuthor paperAndAuthor = new PaperAndAuthor();
        paperAndAuthor.setAuthorId(authorId);
        paperAndAuthor.setPaperId(paperId);
        paperAndAuthorRepository.save(paperAndAuthor);
    }

//    public List<Author> getAuthorsForPaper(Long paperId) throws Exception {
//        List<PaperAndAuthor> paperAndAuthors = paperAndAuthorRepository.findAllByPaperId(paperId);
//        List<Author> authors = new ArrayList<>();
//
//        for(PaperAndAuthor paperAndAuthor : paperAndAuthors) {
//            Author author = authorRepository.findById(paperAndAuthor.getAuthorId()).orElse(null);
//            if(author != null) {
//                authors.add(author);
//            }
//            //authors.add(authorRepository.findById(paperAndAuthor.getAuthorId()).orElse(null));
//        }
//        return authors;
//    }
//    public List<Paper> getPapersForAuthor(Long authorId) throws Exception {
//        List<PaperAndAuthor> paperAndAuthors = paperAndAuthorRepository.findAllByAuthorId(authorId);
//        List<Paper> papers = new ArrayList<>();
//
//        for(PaperAndAuthor paperAndAuthor : paperAndAuthors) {
//            Paper paper = paperRepository.findById(paperAndAuthor.getPaperId()).orElse(null);
//            if(paper != null) {
//                papers.add(paper);
//            }
//            //papers.add(paperRepository.findById(paperAndAuthor.getPaperId()).orElse(null));
//        }
//        return papers;
//    }
}
