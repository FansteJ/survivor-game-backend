package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.GameSessionEnemyKill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameSessionEnemyKillRepository extends JpaRepository<GameSessionEnemyKill, UUID> {
}
