package com.example.techscreening.service;

import com.example.techscreening.model.Album;
import com.example.techscreening.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author basbroerse
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

//    @Override
//    public List<Album> findByArtistId(Long id) {
//        return albumRepository.findByArtistId(id);
//    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album create(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album update(Long id, Album album) {
        Optional<Album> _album = findById(id);

        Album updatedAlbum = _album.get();
        updatedAlbum.setName(album.getName());

        return albumRepository.save(updatedAlbum);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }
}
