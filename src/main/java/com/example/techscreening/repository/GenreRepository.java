package com.example.techscreening.repository;

import com.example.techscreening.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author basbroerse
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
