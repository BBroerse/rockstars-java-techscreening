package com.example.techscreening.dto;

import com.example.techscreening.model.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistDtoUnitTest {

    @Test
    public void whenConvertArtistEntityToArtistDto_thenCorrect() {
        Artist artist = new Artist();
        artist.setName("Bring me the Horizon");

        ArtistDto artistDto = new ArtistDto(artist);
        assertEquals(artist.getName(), artistDto.getName());
    }

    @Test
    public void whenConvertArtistDtoToArtistEntity_thenCorrect() {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Bring me the Horizon");

        Artist artist = artistDto.toEntity();
        assertEquals(artistDto.getName(), artist.getName());
    }
}
