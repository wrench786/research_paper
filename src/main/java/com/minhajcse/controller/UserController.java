package com.minhajcse.controller;

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
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AuthorService authorService;
    public UserController(UserService userService, AuthorService authorService) {
        this.userService = userService;
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser( @RequestBody User user) {
        Boolean flag = userService.existsUser(user.getUserId());
        if(flag) {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Boolean flag = userService.existsUser(id);
        if(flag) {
            userService.deleteUser(id);
            return ResponseEntity.ok("Deleted User");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Id");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
