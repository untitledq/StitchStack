package com.stitchstack.application;

import com.stitchstack.domain.ports.SessionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SessionCleanupService {

    private final SessionRepository sessionRepository;

    public SessionCleanupService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${session.cleanup.fixed-delay-ms:900000}")
    public void cleanupRevokedAndExpiredSessions() {
        sessionRepository.deleteRevokedOrExpired(Instant.now());
    }
}
