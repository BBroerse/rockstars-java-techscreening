package com.example.techscreening.repository;

import com.example.techscreening.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author basbroerse
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT a.* FROM #{#entityName} a WHERE a.name LIKE %?1%", nativeQuery = true)
    List<Artist> findByNameContaining(String name);

}
