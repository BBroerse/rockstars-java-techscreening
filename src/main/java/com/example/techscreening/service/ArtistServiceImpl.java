package com.example.techscreening.service;

import com.example.techscreening.model.Artist;
import com.example.techscreening.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> findByNameContaining(String title) {
        return artistRepository.findByNameContaining(title);
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist update(Long id, Artist artist) {
        Optional<Artist> _artist = findById(id);

        Artist updatedArtist = _artist.get();
        updatedArtist.setName(artist.getName());

        return artistRepository.save(updatedArtist);
    }

    @Override
    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }
}
