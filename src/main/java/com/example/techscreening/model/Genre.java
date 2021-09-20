package com.example.techscreening.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This model represents the database table: genres
 * @author basbroerse
 */
@Entity(name = "genres")
@Table(name = "genres")
public class Genre extends BaseEntity {

    @NotNull
    private String name;

    public Genre() { }

    public Genre(String name) {
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
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}
