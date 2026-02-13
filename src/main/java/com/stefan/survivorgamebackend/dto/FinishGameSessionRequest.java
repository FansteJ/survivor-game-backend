package com.stefan.survivorgamebackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FinishGameSessionRequest {
    private UUID gameSessionId;
    private int durationSeconds;
    private int levelReached;
    private List<EnemyKillDTO> enemiesKilled;
}
