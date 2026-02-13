package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameSessionEnemyKill {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @ManyToOne
    @JoinColumn(name = "enemy_type_id")
    private EnemyType type;

    private int count;
}
