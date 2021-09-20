package com.example.techscreening.controller;

import com.example.techscreening.dto.ArtistDto;
import com.example.techscreening.dto.SongDto;
import com.example.techscreening.model.Artist;
import com.example.techscreening.model.Song;
import com.example.techscreening.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller which handles all HTTP requests for the "/artists" endpoint.
 * Uses SongService to delegate all business logic
 *
 * @author basbroerse
 */
@RestController
@RequestMapping()
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistDto>> getAllArtists(@RequestParam(required = false) String name) {
        try {
            List<Artist> artists = new ArrayList<>();

            if(name == null) {
                artists.addAll(artistService.findAll());
            } else {
                artists.addAll(artistService.findByNameContaining(name));
            }

            if(artists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<ArtistDto> artistDtoList = artists.stream()
                    .map(artist -> new ArtistDto(artist))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(artistDtoList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable("id") Long id) {
        Optional<Artist> artist = artistService.findById(id);

        if(artist.isPresent()) {
            return new ResponseEntity<>(new ArtistDto(artist.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/artists")
    public ResponseEntity<ArtistDto> createArtist(@Valid @RequestBody ArtistDto artistDto) {
        try {
            Artist newArist = artistService.create(artistDto.toEntity());
            return new ResponseEntity<>(new ArtistDto(newArist), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<ArtistDto> putArtist(
            @PathVariable("id") Long id,
            @Valid @RequestBody ArtistDto artistDto
    ) {
        Artist artist = artistDto.toEntity();
        return new ResponseEntity<>(new ArtistDto(artistService.update(id, artist)), HttpStatus.OK);
    }

    @PatchMapping("/artists/{id}")
    public ResponseEntity<ArtistDto> patchArtist(
            @PathVariable("id") Long id,
            @Valid @RequestBody ArtistDto artistDto
    ) {
        // TODO: Implement patch functionality (partial update)
        Artist artist = artistDto.toEntity();
        return new ResponseEntity<>(new ArtistDto(artistService.update(id, artist)), HttpStatus.OK);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<HttpStatus> deleteArtist(@PathVariable("id") Long id) {
        try {
            artistService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
