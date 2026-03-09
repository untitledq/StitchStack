package com.stitchstack.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public record AuthResponse(
        UUID userId,
        String username,
        @JsonProperty("created_at") Instant createdAt,
        String token
) {
}
