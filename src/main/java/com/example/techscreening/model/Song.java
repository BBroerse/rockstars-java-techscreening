package com.example.techscreening.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * This model represents the database table: songs
 *
 * @author basbroerse
 */
@Entity(name = "songs")
@Table(name = "songs")
public class Song extends BaseEntity {

    @NotNull
    private String name;

    private String shortName;

    private Integer year;

    private Integer bpm;

    @Column(name = "spotify_id")
    private String spotifyId;

    @NotNull
    private String genre;

    @NotNull
    private String artist;

    private String album;

    @NotNull
    private Integer duration;


//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "genre_id")
//    private Genre genre;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "artist_id")
//    private Artist artist;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "album_id")
//    private Album album;

    public Song() {
    }

    public Song(Long id, String name, String shortName, int year, int bpm, String spotifyId,
                String genre, String artist, String album, int duration
    ) {
        super.setId(id);
        this.name = name;
        this.shortName = shortName;
        this.year = year;
        this.bpm = bpm;
        this.spotifyId = spotifyId;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public Song(String name, String shortName, int year, int bpm, String spotifyId,
                String genre, String artist, String album, int duration
    ) {
        this.name = name;
        this.shortName = shortName;
        this.year = year;
        this.bpm = bpm;
        this.spotifyId = spotifyId;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", year=" + year +
                ", bpm=" + bpm +
                ", spotifyId='" + spotifyId + '\'' +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                "} " + super.toString();
    }
}
