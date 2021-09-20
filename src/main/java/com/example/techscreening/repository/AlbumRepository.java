package com.example.techscreening.repository;

import com.example.techscreening.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author basbroerse
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

//    List<Album> findByArtistId(Long id);

}
