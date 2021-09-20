package com.example.techscreening.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This model represents the database table: migrations
 * this keeps track of all the sql files that are executed in the database
 *
 * @author basbroerse
 */
@Entity(name = "migrations")
@Table(name = "migrations")
public class Migration {

    @Id
    private String name;

    public Migration() {
    }

    public Migration(String name) {
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
        return "Migration{" +
                "name='" + name + '\'' +
                '}';
    }
}
