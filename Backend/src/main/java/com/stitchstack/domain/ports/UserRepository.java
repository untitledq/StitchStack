package com.stitchstack.domain.ports;

import com.stitchstack.domain.model.User;

public interface UserRepository {

    boolean existsByUsername(String username);
    User save(User user);
}