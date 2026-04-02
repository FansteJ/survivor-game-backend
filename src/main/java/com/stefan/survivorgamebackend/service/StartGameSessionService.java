package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.GameSessionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StartGameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final UserProfileService userProfileService;

    @Transactional
    public UUID startGameSession() {
        UserProfile profile = userProfileService.getCurrentProfile();
        Optional<GameSession> activeSessionOpt = gameSessionRepository.findByProfileAndGameOverFalse(profile);

        if (activeSessionOpt.isPresent()) {
            GameSession abandonedSession = activeSessionOpt.get();
            abandonedSession.setGameOver(true);

            abandonedSession.setDurationSeconds(0);
            abandonedSession.setGoldEarned(0);
            abandonedSession.setXpEarned(0);

            gameSessionRepository.save(abandonedSession);
        }

        GameSession gameSession = new GameSession();
        gameSession.setGameOver(false);
        gameSession.setProfile(profile);
        gameSessionRepository.save(gameSession);
        return gameSession.getId();
    }
}
