package com.stitchstack.infrastructure.security;

import com.stitchstack.domain.model.Session;
import com.stitchstack.domain.ports.SessionRepository;
import com.stitchstack.domain.ports.TokenPayload;
import com.stitchstack.domain.ports.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SessionRepository sessionRepository;

    public JwtAuthenticationFilter(TokenService tokenService, SessionRepository sessionRepository) {
        this.tokenService = tokenService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try{
            TokenPayload tokenPayload = tokenService.parseAndValidate(token);

            Optional<Session> optionalSession = sessionRepository.findByJti(tokenPayload.sessionId());

            if (optionalSession.isPresent()) {
                Session session = optionalSession.get();

                boolean valid = !session.isRevoked()
                                && session.getExpiresAt().isAfter(Instant.now())
                                && session.getToken().equals(token);

                if (valid) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    tokenPayload.username(),
                                    null,
                                    AuthorityUtils.NO_AUTHORITIES
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch (Exception ignored){

        }
        filterChain.doFilter(request, response);
    }
}
