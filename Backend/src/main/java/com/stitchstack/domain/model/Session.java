package com.stitchstack.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Session {
    private UUID jti;
    private UUID userId;
    private String token;
    private Instant expiresAt;
    private Instant issuedAt;
    private boolean revoked;

    public Session(UUID jti, UUID userId, String token, Instant issuedAt, Instant expiresAt, boolean revoked) {
        this.jti = jti;
        this.userId = userId;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
    }

    public UUID getJti() {
        return jti;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public boolean isRevoked() {
        return revoked;
    }
}
