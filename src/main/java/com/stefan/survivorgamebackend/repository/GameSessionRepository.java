package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
    boolean existsGameSessionsByProfileAndGameOverFalse(UserProfile userProfile);
}
