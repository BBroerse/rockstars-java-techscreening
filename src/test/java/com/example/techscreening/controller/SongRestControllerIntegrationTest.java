package com.example.techscreening.controller;


import com.example.techscreening.model.Song;
import com.example.techscreening.repository.SongRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class SongRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SongRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() { repository.deleteAll(); }

    @Test
    public void whenValidInput_thenCreateSong() throws Exception {
        Song song = new Song(
                "Song 1", "songone", 2016, 80, "string",
                "Metal", "Bon Jovi", null, 20000);
        String songString = objectMapper.writeValueAsString(song);
        mvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(songString));

        List<Song> result = repository.findAll();
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), song.getName());
        assertEquals(result.get(0).getShortName(), song.getShortName());
        assertEquals(result.get(0).getYear(), song.getYear());
        assertEquals(result.get(0).getBpm(), song.getBpm());
        assertEquals(result.get(0).getSpotifyId(), song.getSpotifyId());
        assertEquals(result.get(0).getGenre(), song.getGenre());
        assertEquals(result.get(0).getArtist(), song.getArtist());
        assertEquals(result.get(0).getAlbum(), song.getAlbum());
        assertEquals(result.get(0).getDuration(), song.getDuration());
    }

    @Test
    public void whenInvalidInput_thenStatus400() throws Exception {
        String content = "{invalid: 'input'}";
        mvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenMalformedInput_thenStatus400() throws Exception {
        String content = "{malformed: input';}";
        mvc.perform(post("/songs").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenSongs_whenGetSongs_thenStatus200() throws Exception {
        Song song1 = new Song(
                "Song 1", "songone", 2016, 80, "string",
                "Metal", "Bon Jovi", null, 20000);

        Song song2 = new Song(
                "Song 2", "songtwo", 2016, 80, "string",
                "Metal", "Travis Barker", null, 20000);

        repository.save(song1);
        repository.save(song2);

        mvc.perform(get("/songs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(song1.getName())))
                .andExpect(jsonPath("$[0].shortName", is(song1.getShortName())))
                .andExpect(jsonPath("$[0].year", is(song1.getYear())))
                .andExpect(jsonPath("$[0].bpm", is(song1.getBpm())))
                .andExpect(jsonPath("$[0].spotifyId", is(song1.getSpotifyId())))
                .andExpect(jsonPath("$[0].genre", is(song1.getGenre())))
                .andExpect(jsonPath("$[0].artist", is(song1.getArtist())))
                .andExpect(jsonPath("$[0].album", is(song1.getAlbum())))
                .andExpect(jsonPath("$[0].duration", is(song1.getDuration())))

                .andExpect(jsonPath("$[1].name", is(song2.getName())))
                .andExpect(jsonPath("$[1].shortName", is(song2.getShortName())))
                .andExpect(jsonPath("$[1].year", is(song2.getYear())))
                .andExpect(jsonPath("$[1].bpm", is(song2.getBpm())))
                .andExpect(jsonPath("$[1].spotifyId", is(song2.getSpotifyId())))
                .andExpect(jsonPath("$[1].genre", is(song2.getGenre())))
                .andExpect(jsonPath("$[1].artist", is(song2.getArtist())))
                .andExpect(jsonPath("$[1].album", is(song2.getAlbum())))
                .andExpect(jsonPath("$[1].duration", is(song2.getDuration())));
    }

    @Test
    public void whenNoSongs_thenEmptyBody() throws Exception {
        mvc.perform(get("/songs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenSong_whenPatch_thenStatus200() throws Exception {
        Song song = new Song(
                "Song 1", "songone", 2016, 80, "string",
                "Metal", "Bon Jovi", null, 20000);
        Song savedSong = repository.save(song);

        String url = String.format("/songs/%s", savedSong.getId());

        Song updatedSong = song;
        updatedSong.setName("Updated name");
        String updatedSongString = objectMapper.writeValueAsString(updatedSong);

        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(updatedSongString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(Object.class)))
                .andExpect(jsonPath("$.id", is(""+updatedSong.getId())))
                .andExpect(jsonPath("$.name", is(song.getName())))
                .andExpect(jsonPath("$.shortName", is(song.getShortName())))
                .andExpect(jsonPath("$.year", is(song.getYear())))
                .andExpect(jsonPath("$.bpm", is(song.getBpm())))
                .andExpect(jsonPath("$.spotifyId", is(song.getSpotifyId())))
                .andExpect(jsonPath("$.genre", is(song.getGenre())))
                .andExpect(jsonPath("$.artist", is(song.getArtist())))
                .andExpect(jsonPath("$.album", is(song.getAlbum())))
                .andExpect(jsonPath("$.duration", is(song.getDuration())));
    }
}
