package com.example.techscreening.dto;

import com.example.techscreening.model.Song;

import java.util.Date;

public class SongDto {

    private Long id;
    private String name;
    private String shortName;
    private Integer year;
    private Integer bpm;
    private String spotifyId;
    private String genre;
    private String artist;
    private String album;
    private Integer duration;
    private Date createdAt;
    private Date updatedAt;

    public SongDto() {
    }

    public SongDto(Song entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.shortName = entity.getShortName();
        this.year = entity.getYear();
        this.bpm = entity.getBpm();
        this.spotifyId = entity.getSpotifyId();
        this.genre = entity.getGenre();
        this.artist = entity.getArtist();
        this.album = entity.getAlbum();
        this.duration = entity.getDuration();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public Song toEntity() {
        Song song = new Song();

        song.setId(this.getId());
        song.setName(this.getName());
        song.setShortName(this.getShortName());
        song.setBpm(this.getBpm());
        song.setYear(this.getYear());
        song.setSpotifyId(this.getSpotifyId());
        song.setGenre((this.getGenre()));
        song.setArtist(this.getArtist());
        song.setAlbum(this.getAlbum());
        song.setDuration(this.getDuration());

        song.setCreatedAt(this.getCreatedAt());
        song.setUpdatedAt(this.getUpdatedAt());

        return song;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
