package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
}
