package com.stitchstack.web.request;

public record RegisterRequest(
        String username,
        String password,
        String email
) {
}