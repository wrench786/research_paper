package com.minhajcse.service;

import com.minhajcse.exception.UserNotFoundException;
import com.minhajcse.model.User;
import com.minhajcse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Long createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public boolean existsUser(Long id){
        return userRepository.existsById(id);
    }

}
