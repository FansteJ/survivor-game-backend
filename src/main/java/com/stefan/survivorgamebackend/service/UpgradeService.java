package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.BuyUpgradeResponse;
import com.stefan.survivorgamebackend.model.*;
import com.stefan.survivorgamebackend.repository.UpgradeTypeRepository;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import com.stefan.survivorgamebackend.service.strategy.CostCalculationStrategy;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UpgradeService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UpgradeTypeRepository upgradeTypeRepository;
    private final UserUpgradeRepository userUpgradeRepository;

    private final Map<ScalingType, CostCalculationStrategy> costStrategies;

    public UpgradeService(
            UserRepository userRepository,
            UserProfileRepository userProfileRepository,
            UpgradeTypeRepository upgradeTypeRepository,
            UserUpgradeRepository userUpgradeRepository,
            List<CostCalculationStrategy> strategies
    ) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.upgradeTypeRepository = upgradeTypeRepository;
        this.userUpgradeRepository = userUpgradeRepository;

        this.costStrategies = strategies.stream()
                .collect(Collectors.toMap(s -> s.getType(), s -> s));
    }

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

    public long calculateCost(UpgradeType upgradeType, int level) {
        CostCalculationStrategy strategy = costStrategies.get(upgradeType.getScalingType());
        if(strategy == null) {
            throw new RuntimeException("Unknown scaling type");
        }
        return strategy.calculateCost(upgradeType.getBaseCost(), level, upgradeType.getScalingFactor());
    }
}
