package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.BuyUpgradeResponse;
import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import com.stefan.survivorgamebackend.repository.UpgradeTypeRepository;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpgradeService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UpgradeTypeRepository upgradeTypeRepository;
    private final UserUpgradeRepository userUpgradeRepository;

    @Transactional
    public BuyUpgradeResponse buyUpgrade(UUID upgradeTypeId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserProfile profile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        UpgradeType upgradeType = upgradeTypeRepository.findById(upgradeTypeId)
                .orElseThrow(() -> new RuntimeException("Upgrade type not found"));
        UserUpgrade upgrade = userUpgradeRepository.findByUserProfileAndUpgradeType(profile, upgradeType)
                .orElseGet(() -> {
                    UserUpgrade userUpgrade = new UserUpgrade();
                    userUpgrade.setUpgradeType(upgradeType);
                    userUpgrade.setLevel(0);
                    userUpgrade.setUserProfile(profile);
                    return userUpgrade;
                });
        long gold = profile.getGold();
        int level = upgrade.getLevel();
        if(level >= upgradeType.getMaxLevel()) {
            throw new RuntimeException("Upgrade level exceeds max level");
        }
        long cost = calculateCost(upgradeType, level);
        if(cost > gold){
            throw new RuntimeException("Not enough gold for upgrade");
        }

        profile.setGold(gold - cost);
        upgrade.setLevel(upgrade.getLevel()+1);
        userProfileRepository.save(profile);
        userUpgradeRepository.save(upgrade);

        return new BuyUpgradeResponse(upgrade.getLevel(), profile.getGold());
    }

    private long calculateCost(UpgradeType upgradeType, int level) {
        return (long) (upgradeType.getBaseCost() * Math.pow(1.5, level));
    }
}
