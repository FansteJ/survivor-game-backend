package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.EnemyKillDTO;
import com.stefan.survivorgamebackend.dto.FinishGameSessionRequest;
import com.stefan.survivorgamebackend.dto.RewardResult;
import com.stefan.survivorgamebackend.model.EnemyType;
import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.GameSessionEnemyKill;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinishGameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final EnemyTypeRepository enemyTypeRepository;
    private final GameSessionEnemyKillRepository killRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    public void finishGameSession(FinishGameSessionRequest request) {
        Optional<GameSession> session =  gameSessionRepository.findById(request.getGameSessionId());
        if (!session.isPresent()) {
            throw new RuntimeException("Game session not found");
        }
        GameSession gameSession = session.get();
        if(gameSession.isGameOver()) {
            throw new RuntimeException("Game session is already finished");
        }

        RewardResult reward = calculateReward(gameSession, request.getEnemiesKilled());

        gameSession.setKills(reward.kills());
        gameSession.setGoldEarned(reward.gold());
        gameSession.setXpEarned(reward.xp());
        gameSession.setDurationSeconds(request.getDurationSeconds());
        gameSession.setLevelReached(request.getLevelReached());
        gameSession.setGameOver(true);
        gameSession.setEndTime(LocalDateTime.now());

        UserProfile profile = gameSession.getProfile();
        profile.setTotalRuns(profile.getTotalRuns() + 1);
        profile.setLevelReached(Math.max(profile.getLevelReached(), request.getLevelReached()));
        profile.setGold(profile.getGold() + reward.gold());
        profile.setTotalXp(profile.getTotalXp() + reward.xp());
        profile.setGems(profile.getGems() + reward.gems());

        userProfileRepository.save(profile);
        gameSessionRepository.save(gameSession);
    }

    private RewardResult calculateReward(GameSession session, List<EnemyKillDTO> enemiesKilled) {
        Map<UUID, EnemyType> enemyTypeMap = enemyTypeRepository.findAll().stream()
                .collect(Collectors.toMap(EnemyType::getId, type -> type));

        long totalGoldEarned = 0;
        long totalXpEarned = 0;
        long totalGems = 0;
        int totalKills = 0;
        for(EnemyKillDTO kill : enemiesKilled) {
            EnemyType type = enemyTypeMap.get(kill.getEnemyTypeId());
            if(type == null) {
                throw new RuntimeException("Enemy type doesn't exist");
            }

            int kills = kill.getCount();
            long gold = kills * type.getBaseGold();
            long xp = kills * type.getBaseXp();
            long gems = kills * type.getBaseGems();
            totalGoldEarned += gold;
            totalXpEarned += xp;
            totalKills += kills;
            totalGems += gems;

            GameSessionEnemyKill enemyKill = new GameSessionEnemyKill();
            enemyKill.setGameSession(session);
            enemyKill.setType(type);
            enemyKill.setCount(kills);
            killRepository.save(enemyKill);
        }
        return new RewardResult(totalGoldEarned, totalXpEarned, totalGems, totalKills);
    }
}
