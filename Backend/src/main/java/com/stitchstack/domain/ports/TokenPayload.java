package com.stitchstack.domain.ports;

import java.time.Instant;
import java.util.UUID;

public record TokenPayload(
        UUID userId,
        String username,
        UUID sessionId,
        Instant expiresAt
) {
}