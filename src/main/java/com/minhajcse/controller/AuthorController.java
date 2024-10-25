package com.minhajcse.controller;

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
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id");
        }
    }

//    @PostMapping("/create/{id}")
//    public ResponseEntity<?> createAuthor(@PathVariable Long id, @RequestBody Author author) {
//        User user = userService.getUserById(id);
//        if(user != null) {
//            try{
//                Author createdAuthor = authorService.createAuthor(author);
//                user.setAuthorId(createdAuthor.getAuthorId());
//                userService.updateUser(user);
//
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
//            }
//            catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already exists with this ID");
//            }
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id");
//        }
//    }


    @PostMapping("/create/{id}")
    public ResponseEntity<?> createAuthor(@PathVariable Long id, @RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author dummyAuthor = authorService.getAuthorById(id);
        if(dummyAuthor != null) {
            author.setAuthorId(id);
            Author updatedAuthor = authorService.updateAuthor(author);
            return ResponseEntity.ok(updatedAuthor);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Author Id");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Author deleted Successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Author Id");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
}
