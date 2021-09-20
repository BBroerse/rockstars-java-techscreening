package com.example.techscreening.repository;

import com.example.techscreening.model.Artist;
import com.example.techscreening.model.Genre;
import com.example.techscreening.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author basbroerse
 */
public interface SongRepository extends JpaRepository<Song, Long> {

//    List<Song> findByGenre_Name(String genreName);

    @Query(value = "SELECT s.* FROM #{#entityName} s WHERE s.genre LIKE %?1%", nativeQuery = true)
    List<Song> findByGenreContaining(String genre);

}
