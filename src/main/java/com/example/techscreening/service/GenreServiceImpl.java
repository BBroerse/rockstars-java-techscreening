package com.example.techscreening.service;

import com.example.techscreening.model.Genre;
import com.example.techscreening.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Long id, Genre genre) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
