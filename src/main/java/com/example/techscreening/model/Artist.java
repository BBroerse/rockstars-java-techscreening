package com.example.techscreening.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * This model represents the database table: artists
 * @author basbroerse
 */
@Entity(name = "artists")
@Table(name = "artists")
public class Artist extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String name;

//    @OneToMany(mappedBy = "artist")
//    private Set<Album> albums;
//
//    @OneToMany(mappedBy = "artist")
//    private Set<Song> songs;

    public Artist() {
    }

    public Artist(Long id, String name) {
        super.setId(id);
        this.name = name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
