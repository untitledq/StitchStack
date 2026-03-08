package com.stitchstack.application;

import com.stitchstack.domain.model.User;
import com.stitchstack.domain.ports.PasswordHasher;
import com.stitchstack.domain.ports.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordHasher passwordHasher;

    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User register(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists");
        }

        String passwordHash = passwordHasher.hash(password);

        User user = new User(
                UUID.randomUUID(),
                username,
                passwordHash,
                email
        );

        return userRepository.save(user);
    }

    public String login(String username, String password) {
        return username;
    }
}