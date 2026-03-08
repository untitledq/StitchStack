package com.stitchstack.domain.model;

import java.time.Instant;
import java.util.*;

public class User {

    private UUID id;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdAt;

    public User(){ }

    public User(UUID id,
                String username,
                String passwordHash,
                String firstName,
                String lastName,
                String email,
                Instant createdAt){
        setId(id);
        setUsername(username);
        setPasswordHash(passwordHash);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setCreatedAt(createdAt);
    }

    public void setId(UUID id) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username must not be null");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null) throw new IllegalArgumentException("passwordHash must not be null");
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("firstName must not be null");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("lastName must not be null");
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email must not be null");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCreatedAt(Instant createdAt) {
        if (createdAt == null) throw new IllegalArgumentException("createdAt must not be null");
        this.createdAt = createdAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User other)) return false;
        return Objects.equals(id, other.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}

