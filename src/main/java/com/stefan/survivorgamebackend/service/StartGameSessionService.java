package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.GameSessionRepository;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StartGameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    public UUID startGameSession() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }
        Optional<UserProfile> userProfile = userProfileRepository.findByUser(user.get());
        if(!userProfile.isPresent()) {
            throw new RuntimeException("UserProfile not found");
        }
        if(gameSessionRepository.existsGameSessionsByProfileAndGameOverFalse(userProfile.get())) {
            throw new RuntimeException("Active game session already exists");
        }

        GameSession gameSession = new GameSession();
        gameSession.setGameOver(false);
        gameSession.setProfile(userProfile.get());
        gameSessionRepository.save(gameSession);
        return gameSession.getId();
    }
}
