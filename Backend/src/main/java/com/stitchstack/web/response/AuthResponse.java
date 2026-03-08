package com.stitchstack.web.response;

import java.util.UUID;

public record AuthResponse(
        UUID userId,
        String username,
        String token
) {
}