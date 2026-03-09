package com.stitchstack.application;

import com.stitchstack.domain.model.User;
import com.stitchstack.domain.ports.PasswordHasher;
import com.stitchstack.domain.ports.UserRepository;
import com.stitchstack.util.UUIDv7;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
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
        validateCredentials(username, password);
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists");
        }

        String passwordHash = passwordHasher.hash(password);

        User user = new User(
                UUIDv7.randomUUID(),
                username,
                passwordHash,
                email,
                Instant.now()
        );

        return userRepository.save(user);
    }

    public String login(String username, String rawPassword) {
        validateCredentials(username, rawPassword);
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new SecurityException("Username not found");
        }

        User u =  user.get();

        if (!passwordHasher.matches(rawPassword, u.getPassword())) {
            throw new SecurityException("Invalid credentials");
        }

        return u.getUsername();
    }


    private void validateCredentials(String username, String rawPassword) {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("username blank");
        if (rawPassword == null || rawPassword.isBlank())
            throw new IllegalArgumentException("password blank");
    }
}