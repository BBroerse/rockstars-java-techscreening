package com.example.techscreening.service;

import com.example.techscreening.model.Artist;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author basbroerse
 */
public interface ArtistService {

    List<Artist> findAll();
    List<Artist> findByNameContaining(String title);
    Optional<Artist> findById(Long id);
    Artist create(Artist artist);
    Artist update(Long id, Artist artist);
    void deleteById(Long id);

}
