package com.example.techscreening.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This model represents the database table:
 * @author basbroerse
 */
@Entity(name = "albums")
@Table(name = "albums")
public class Album extends BaseEntity {

    @NotNull
    private String name;


    private Artist artist;

    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artist=" + artist +
                '}';
    }
}
