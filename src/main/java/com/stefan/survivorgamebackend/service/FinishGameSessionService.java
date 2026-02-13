package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.FinishGameSessionRequest;
import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.repository.GameSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FinishGameSessionService {
    private final GameSessionRepository gameSessionRepository;

    public FinishGameSessionService(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }


    public void finishGameSession(FinishGameSessionRequest request) {
        Optional<GameSession> session =  gameSessionRepository.findById(request.getGameSessionId());
        if (!session.isPresent()) {
            throw new RuntimeException("Game session not found");
        }
        GameSession gameSession = session.get();
        if(gameSession.isGameOver()) {
            throw new RuntimeException("Game session is already finished");
        }

        // calculate rewards

        gameSession.setDurationSeconds(request.getDurationSeconds());
        gameSession.setLevelReached(request.getLevelReached());
        gameSession.setGameOver(true);
        gameSession.setEndTime(LocalDateTime.now());
        gameSessionRepository.save(gameSession);
    }
}
