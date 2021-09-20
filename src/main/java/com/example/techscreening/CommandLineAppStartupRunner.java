package com.example.techscreening;

import com.example.techscreening.model.Artist;
import com.example.techscreening.model.Migration;
import com.example.techscreening.model.Song;
import com.example.techscreening.repository.ArtistRepository;
import com.example.techscreening.repository.MigrationRepository;
import com.example.techscreening.repository.SongRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Runs after startup and checks all data files from "/resources/db/seeds" against the
 * migration's database table. If a file isn't seeded yet, it performs a bulk insert.
 *
 * @author basbroerse
 */
@Component
@Profile("!test") // FIXME: Don't run while doing automation tests
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    MigrationRepository migrationRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public void run(String...args) throws Exception {
        loadInitialDatabaseData();
    }

    /**
     * Reads all files in the /resources/db/seed directory and insert them into
     * the database only once by checking the migrations table.
     *
     *  TODO: Make everything dynamic -> index files in directory, load and insert them
     *  TODO: make fail safe -> transactions -> error handling
     *  TODO: improve performance -> map for faster lookups(?)
     * @throws IOException
     */
    private void loadInitialDatabaseData() throws IOException {
        List<Migration> migrations =  migrationRepository.findAll();

        Migration artistsMigration = findMigrationInList(migrations, "001_artists.json");
        Migration songsMigration = findMigrationInList(migrations, "002_songs.json");

        System.out.println(artistsMigration);
        System.out.println(songsMigration);

        if(!Objects.isNull(artistsMigration) && !Objects.isNull(songsMigration)) {
            System.out.println("No new migrations. Stop task");
            return;
        }

        if(Objects.isNull(artistsMigration)) {
            initLoadArtists();
        }

        if(Objects.isNull(songsMigration)) {
            initLoadSongs();
        }
    }

    private JsonArray getArrayFromJsonFile(String jsonData) {
        return (JsonArray) JsonParser.parseString(jsonData);
    }

    private String loadContentFromFile(String fileUrl) throws IOException {
        File resource = new ClassPathResource(fileUrl).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    private Migration findMigrationInList(List<Migration> migrations, String migrationName) {
        return migrations.stream()
                .filter(item -> item.getName().equals(migrationName))
                .findAny()
                .orElse(null);
    }

    private void initLoadArtists() throws IOException {
        System.out.println("Load artists into db.");
        String artistsString = loadContentFromFile("db/seed/001_artists.json");

        JsonArray artistsJsonArray = getArrayFromJsonFile(artistsString);
        List<Artist> artists = new ArrayList<>();

        for (Object object : artistsJsonArray) {
            JsonObject artistObject = (JsonObject) object;
            artists.add(new Artist(
                    artistObject.get("Id").getAsLong(),
                    artistObject.get("Name").getAsString()
            ));
        }

        artistRepository.saveAll(artists);
        migrationRepository.save(new Migration("001_artists.json"));
    }

    private void initLoadSongs() throws IOException {
        System.out.println("Load songs into db.");
        String songsString = loadContentFromFile("db/seed/002_songs.json");

        JsonArray songsJsonArray = getArrayFromJsonFile(songsString);
        List<Song> songs = new ArrayList<>();

        int SONG_MAX_YEAR = 2016;
        String GENRE_CONTAINS_METAL = "metal";

        for (Object object : songsJsonArray) {
            JsonObject artistObject = (JsonObject) object;

            Boolean genreContainsMetal = artistObject.get("Genre").getAsString().toLowerCase().contains(GENRE_CONTAINS_METAL);
            Boolean songReleasedBeforeMaxYear = artistObject.get("Year").getAsInt() < SONG_MAX_YEAR;

            if(!genreContainsMetal || !songReleasedBeforeMaxYear) {
                continue;
            }

            songs.add(new Song(
                    artistObject.get("Id").getAsLong(),
                    artistObject.get("Name").getAsString(),
                    artistObject.get("Shortname").getAsString(),
                    artistObject.get("Year").getAsInt(),
                    artistObject.get("Bpm").isJsonNull()
                            ? null
                            : artistObject.get("Bpm").getAsInt(),
                    artistObject.get("SpotifyId").isJsonNull()
                            ? null
                            : artistObject.get("SpotifyId").getAsString(),
                    artistObject.get("Genre").getAsString(),
                    artistObject.get("Artist").getAsString(),
                    artistObject.get("Album").isJsonNull()
                            ? null
                            : artistObject.get("Album").getAsString(),
                    artistObject.get("Duration").getAsInt()
            ));
        }

        songRepository.saveAll(songs);
        migrationRepository.save(new Migration("002_songs.json"));
    }


}
