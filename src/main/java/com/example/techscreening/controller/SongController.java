package com.example.techscreening.controller;

import com.example.techscreening.dto.SongDto;
import com.example.techscreening.model.Song;
import com.example.techscreening.service.SongService;
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
 * Controller which handles all HTTP requests for the "/songs" endpoint.
 * Uses SongService to delegate all business logic
 *
 * @author basbroerse
 */
@RestController
@RequestMapping()
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping("/songs")
    public ResponseEntity<List<SongDto>> getAllSongs(@RequestParam(required = false) String genre) {
        try {
            List<Song> songs = new ArrayList<>();

            if(genre == null) {
                songs.addAll(songService.findAll());
            } else {
                songs.addAll(songService.findByGenreContaining(genre));
            }

            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<SongDto> songDtoList = songs.stream()
                    .map(song -> new SongDto(song))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(songDtoList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongDto> getSongById(@PathVariable("id") Long id) {
        Optional<Song> song = songService.findById(id);

        if(song.isPresent()) {
            return new ResponseEntity<>(new SongDto(song.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/songs")
    public ResponseEntity<SongDto> createSong(@Valid @RequestBody SongDto songDto) {
        try {
            Song newSong = songService.create(songDto.toEntity());
            return new ResponseEntity<>(new SongDto(newSong), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<SongDto> putSong(
            @PathVariable("id") Long id,
            @Valid @RequestBody SongDto songDto
    ) {
        Song song = songDto.toEntity();
        return new ResponseEntity<>(new SongDto(songService.update(id, song)), HttpStatus.OK);
    }

    @PatchMapping("/songs/{id}")
    public ResponseEntity<SongDto> patchSong(
            @PathVariable("id") Long id,
            @Valid @RequestBody SongDto songDto
    ) {
        // TODO: Implement patch functionality (partial update)
        Song song = songDto.toEntity();
        return new ResponseEntity<>(new SongDto(songService.update(id, song)), HttpStatus.OK);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<HttpStatus> deleteSong(@PathVariable("id") Long id) {
        try {
            songService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
