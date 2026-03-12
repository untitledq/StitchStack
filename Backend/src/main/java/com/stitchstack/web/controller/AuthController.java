package com.stitchstack.web.controller;

import com.stitchstack.application.AuthService;
import com.stitchstack.domain.model.User;
import com.stitchstack.application.UserService;
import com.stitchstack.web.request.LoginRequest;
import com.stitchstack.web.request.RegisterRequest;
import com.stitchstack.web.response.AuthResponse;
import com.stitchstack.web.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody RegisterRequest request) {
        User user = userService.register(
                request.username(),
                request.password(),
                request.email()
        );
        LoginResponse login = authService.login(
                request.username(),
                request.password()
        );

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getCreatedAt(),
                login.token()
        );
    }

    @PostMapping("/user/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(
                request.username(),
                request.password()
        );
    }

    @PostMapping("/user/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return;
        }

        authService.logout(header.substring(7));
    }
}
