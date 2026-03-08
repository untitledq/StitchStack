package com.stitchstack.web.controller;

import com.stitchstack.application.UserService;
import com.stitchstack.web.request.LoginRequest;
import com.stitchstack.web.request.RegisterRequest;
import com.stitchstack.web.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public  AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String username = userService.register(
                request.username(),
                request.password(),
                request.email()
        );
        return new AuthResponse(
                null,
                username,
                "dummy-token"
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String username = userService.login(
                request.username(),
                request.password()
        );

        return new AuthResponse(
                null,
                username,
                "dummy-token"
        );
    }
}