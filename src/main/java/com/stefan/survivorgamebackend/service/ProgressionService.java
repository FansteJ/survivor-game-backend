package com.stefan.survivorgamebackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgressionService {
    private static final int BASE_XP = 100;

    public int calculateLevel(long totalXp){
        if(totalXp < BASE_XP){
            return 0;
        }

        return (int) Math.sqrt((double) totalXp / BASE_XP);
    }

    public long xpInCurrentLevel(long totalXp){
        int level = calculateLevel(totalXp);
        return totalXp - xpRequiredForLevel(level);
    }

    public long xpNeededForNextLevel(long totalXp){
        int level = calculateLevel(totalXp);
        return xpRequiredForLevel(level+1) - xpRequiredForLevel(level);
    }

    public long xpRequiredForLevel(int level){
        return (long) BASE_XP * level * level;
    }
}
