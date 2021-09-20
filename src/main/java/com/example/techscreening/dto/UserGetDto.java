package com.example.techscreening.dto;

import com.example.techscreening.model.User;

import java.io.Serializable;
import java.util.Date;

public class UserGetDto implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public UserGetDto() { }

    public UserGetDto(User entity) {
        this.id = entity.getId();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.email = entity.getEmail();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public User toEntity() {
        User entity = new User();

        entity.setId(this.getId());
        entity.setFirstname(this.getFirstname());
        entity.setLastname(this.getLastname());
        entity.setEmail(this.getEmail());

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
