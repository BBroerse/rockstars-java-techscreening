package com.example.techscreening.service;

import com.example.techscreening.model.Genre;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
public interface GenreService {

    List<Genre> findAll();
    Optional<Genre> findById(Long id);
    Genre create(Genre genre);
    Genre update(Long id, Genre genre);
    void deleteById(Long id);

}
