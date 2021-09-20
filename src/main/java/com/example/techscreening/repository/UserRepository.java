package com.example.techscreening.repository;

import com.example.techscreening.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author basbroerse
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
