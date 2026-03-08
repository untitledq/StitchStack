package com.stitchstack.domain.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String username;
    private final String password;
    private final String email;

    public User(UUID id, String username, String password, String email) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password must not be blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email must not be blank");
        }

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}