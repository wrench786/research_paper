package com.minhajcse.repository;

import com.minhajcse.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    List<User> findAll();
    Long save(User user);
    void update(User user);
    void deleteById(Long id);
    boolean existsById(Long id);
}
