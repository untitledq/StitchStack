package com.stitchstack.application;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String register(String username, String password, String email) {
        return username;
    }

    public String login(String username, String password) {
        return username;
    }
}