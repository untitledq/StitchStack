package com.stitchstack.infrastructure.persistence;

import com.stitchstack.infrastructure.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataSessionJpaRepository extends JpaRepository<SessionEntity, UUID> {
    Optional<SessionEntity> findByJti(UUID jti);
    Optional<SessionEntity> findByToken(String token);
    void deleteByExpiresAtBefore(Instant now);
    void deleteByRevokedTrueOrExpiresAtBefore(Instant now);
}
