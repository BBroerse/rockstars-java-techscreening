package com.example.techscreening.service;

import com.example.techscreening.model.Song;
import com.example.techscreening.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findByGenreContaining(String genre) {
        return songRepository.findByGenreContaining(genre);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song create(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song update(Long id, Song song) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
