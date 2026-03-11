package com.stitchstack.infrastructure.persistence;

import com.stitchstack.domain.model.User;
import com.stitchstack.domain.ports.UserRepository;
import com.stitchstack.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepositoryAdapter implements UserRepository {

    private final SpringDataUserJpaRepository repository;

    public JpaUserRepositoryAdapter(SpringDataUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        UserEntity saved = repository.save(toEntity(user));
        return toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getCreatedAt()
        );
    }
}