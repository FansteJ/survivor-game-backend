package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.*;
import com.stefan.survivorgamebackend.model.EnemyType;
import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.GameSessionEnemyKill;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinishGameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final EnemyTypeRepository enemyTypeRepository;
    private final GameSessionEnemyKillRepository killRepository;
    private final UserProfileRepository userProfileRepository;

    private final UpgradeEffectService upgradeEffectService;
    private final QuestService questService;

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

        UserProfile profile = gameSession.getProfile();
        PlayerModifiers modifiers = upgradeEffectService.calculateModifiers(profile);
        RewardResult reward = calculateReward(gameSession, request.getEnemiesKilled(), modifiers);

        gameSession.setKills(reward.kills());
        gameSession.setGoldEarned(reward.gold());
        gameSession.setXpEarned(reward.xp());
        gameSession.setDurationSeconds(request.getDurationSeconds());
        gameSession.setLevelReached(request.getLevelReached());
        gameSession.setGameOver(true);
        gameSession.setEndTime(LocalDateTime.now());

        profile.setTotalRuns(profile.getTotalRuns() + 1);
        profile.setLevelReached(Math.max(profile.getLevelReached(), request.getLevelReached()));
        profile.setGold(profile.getGold() + reward.gold());
        profile.setTotalXp(profile.getTotalXp() + reward.xp());
        profile.setGems(profile.getGems() + reward.gems());
        questService.updateQuestProgress(profile, new QuestProgressDTO(reward.kills(), reward.gold(), gameSession.getLevelReached()));

        userProfileRepository.save(profile);
        gameSessionRepository.save(gameSession);
    }

    private RewardResult calculateReward(GameSession session, List<EnemyKillDTO> enemiesKilled, PlayerModifiers modifiers) {
        List<UUID> killedEnemyIds = enemiesKilled.stream().map(EnemyKillDTO::getEnemyTypeId).toList();
        List<EnemyType> relevantEnemies = enemyTypeRepository.findAllById(killedEnemyIds);

        Map<UUID, EnemyType> enemyTypeMap = relevantEnemies.stream()
                .collect(Collectors.toMap(EnemyType::getId, type -> type));

        long totalGoldEarned = 0;
        long totalXpEarned = 0;
        long totalGems = 0;
        int totalKills = 0;

        List<GameSessionEnemyKill> killsToSave = new ArrayList<>();
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
            killsToSave.add(enemyKill);
        }
        killRepository.saveAll(killsToSave);
        return new RewardResult((long) (totalGoldEarned * modifiers.goldMultiplier()), (long) (totalXpEarned * modifiers.xpMultiplier()), totalGems, totalKills);
    }
}
