package com.example.techscreening.service;

import com.example.techscreening.model.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    User create(User user);
    User update(Long id, User user);
    void deleteById(Long id);

}
