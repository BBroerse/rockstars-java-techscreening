package com.example.techscreening.service;

import com.example.techscreening.model.Song;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
public interface SongService {

    List<Song> findAll();
    List<Song> findByGenreContaining(String genre);
    Optional<Song> findById(Long id);

    Song create(Song song);
    Song update(Long id, Song song);

    void deleteById(Long id);
}
