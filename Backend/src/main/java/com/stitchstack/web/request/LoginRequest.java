package com.stitchstack.web.request;

public record LoginRequest(
        String username,
        String password
) {
}