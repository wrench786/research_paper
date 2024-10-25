package com.minhajcse.controller;

import com.minhajcse.exception.AuthorNotFoundException;
import com.minhajcse.exception.UserNotFoundException;
import com.minhajcse.model.Author;
import com.minhajcse.model.User;
import com.minhajcse.service.AuthorService;
import com.minhajcse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final UserService userService;
    public AuthorController(AuthorService authorService, UserService userService) {
        this.authorService = authorService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {
        try{
            Author author = authorService.getAuthorById(id);
            return ResponseEntity.ok(author);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createAuthor(@PathVariable Long id, @RequestBody Author author) throws UserNotFoundException, AuthorNotFoundException {
        User user = userService.getUserById(id);

        Long authorId = authorService.createAuthor(author);
        Author createdAuthor = authorService.getAuthorById(authorId);
        user.setAuthorId(authorId);
        userService.updateUser(user);

        return ResponseEntity.ok(createdAuthor);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody Author author) {
        boolean flag = authorService.existsAuthor(author.getAuthorId());
        if(flag){
            authorService.updateAuthor(author);
            return ResponseEntity.ok(author);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Author Id");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        boolean flag = authorService.existsAuthor(id);
        if(flag){
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("Deleted Author");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Author Id");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
}
