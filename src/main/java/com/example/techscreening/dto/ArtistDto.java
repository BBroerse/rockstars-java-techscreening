package com.example.techscreening.dto;

import com.example.techscreening.model.Artist;

import java.util.Date;

/**
 * Maps the (raw) model data into a representational view
 *
 * @author basbroerse
 */
public class ArtistDto {

    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public ArtistDto() {
    }

    public ArtistDto(Artist entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Artist toEntity() {
        Artist artist = new Artist();

        artist.setId(this.getId());
        artist.setName(this.getName());
        artist.setCreatedAt(this.getCreatedAt());
        artist.setUpdatedAt(this.getUpdatedAt());

        return artist;
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
}
