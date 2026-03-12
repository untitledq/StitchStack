package com.stitchstack.domain.ports;

import com.stitchstack.domain.model.Session;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {
    Session save(Session session);
    Optional<Session> findByJti(UUID jti);
    Optional<Session> findByToken(String token);
    void revokeByJti(UUID jti);
    //void revokeAllActiveByUserId(UUID userId);
    void deleteExpired(Instant now);
    void deleteRevokedOrExpired(Instant now);
}
