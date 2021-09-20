package com.example.techscreening.dto;

import com.example.techscreening.model.User;

import java.io.Serializable;
import java.util.Set;

public class UserPostDto implements Serializable {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public UserPostDto() { }

    public UserPostDto(User entity) {
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    public User toEntity() {
        User entity = new User();

        entity.setFirstname(this.getFirstname());
        entity.setLastname(this.getLastname());
        entity.setEmail(this.getEmail());
        entity.setPassword(this.getPassword());

        return entity;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
