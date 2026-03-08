package com.stitchstack.application;

import com.stitchstack.domain.model.User;
import com.stitchstack.domain.ports.UserRepository;
import com.stitchstack.util.UUIDv7;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists");
        }

        User user = new User(
                UUIDv7.randomUUID(),
                username,
                password,
                email
        );

        return userRepository.save(user);
    }

    public String login(String username, String password) {
        return username;
    }
}