package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.PlayerModifiers;
import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpgradeEffectService {
    private final UserUpgradeRepository userUpgradeRepository;

    public PlayerModifiers calculateModifiers(UserProfile profile) {
        double goldMultiplier = 1.0;
        double xpMultiplier = 1.0;
        double damageMultiplier = 1.0;
        double luckMultiplier = 1.0;
        double startHpBonus = 0.0;
        int revives = 0;
        double speedBonus = 0.0;

        List<UserUpgrade> upgrades = userUpgradeRepository.findByUserProfile(profile);

        for (UserUpgrade upgrade : upgrades) {
            UpgradeType type = upgrade.getUpgradeType();
            switch(type.getEffectType()){
                case REVIVE:
                    revives += upgrade.getLevel();
                    break;
                case START_HP:
                    startHpBonus += upgrade.getLevel() * type.getValue();
                    break;
                case START_SPEED:
                    speedBonus += upgrade.getLevel() * type.getValue();
                    break;
                case XP_MULTIPLIER:
                    xpMultiplier += upgrade.getLevel() * type.getValue();
                    break;
                case DAMAGE_MULTIPLIER:
                    damageMultiplier += upgrade.getLevel() * type.getValue();
                    break;
                case GOLD_MULTIPLIER:
                    goldMultiplier += upgrade.getLevel() * type.getValue();
                    break;
                case LUCK_MULTIPLIER:
                    luckMultiplier += upgrade.getLevel() * type.getValue();
                    break;
                default:
                    break;
            }
        }

        return new PlayerModifiers(goldMultiplier, xpMultiplier, damageMultiplier,
                startHpBonus, revives, luckMultiplier, speedBonus);
    }
}
