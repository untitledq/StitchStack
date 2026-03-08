package com.stitchstack.domain.ports;

public interface PasswordHasher {
    String hash(String rawPassword);
}
