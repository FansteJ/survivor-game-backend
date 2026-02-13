package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameSession {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private UserProfile profile;

    @OneToMany(mappedBy = "gameSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSessionEnemyKill> sessionEnemyKills;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int durationSeconds;
    private int kills;
    private int levelReached;

    private long goldEarned;
    private long xpEarned;

    private boolean gameOver;
}
