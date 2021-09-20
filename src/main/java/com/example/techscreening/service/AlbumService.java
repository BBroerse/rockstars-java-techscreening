package com.example.techscreening.service;

import com.example.techscreening.model.Album;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
public interface AlbumService {

    List<Album> findAll();
//    List<Album> findByArtistId(Long id);
    Optional<Album> findById(Long id);
    Album create(Album album);
    Album update(Long id, Album album);
    void deleteById(Long id);

}
