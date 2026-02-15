package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.UpgradeShopItemDTO;
import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import com.stefan.survivorgamebackend.repository.UpgradeTypeRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopService {
    private final UserProfileService userProfileService;
    private final UpgradeTypeRepository upgradeTypeRepository;
    private final UserUpgradeRepository userUpgradeRepository;
    private final UpgradeService upgradeService;

    public List<UpgradeShopItemDTO> getShopItems() {
        UserProfile profile = userProfileService.getCurrentProfile();
        List<UpgradeType> upgradeTypes = upgradeTypeRepository.findAll();
        List<UserUpgrade> userUpgrades = userUpgradeRepository.findByUserProfile(profile);

        Map<UUID, UserUpgrade> userUpgradeMap = userUpgrades.stream()
                .collect(Collectors.toMap(u -> u.getUpgradeType().getId(), u -> u));

        List<UpgradeShopItemDTO> upgradeShopItems = new ArrayList<>();

        for(UpgradeType upgradeType : upgradeTypes) {
            UserUpgrade upgrade = userUpgradeMap.get(upgradeType.getId());
            int level;
            if(upgrade != null) {
                level = upgrade.getLevel();
            } else{
                level = 0;
            }
            long cost = upgradeService.calculateCost(upgradeType, level);
            boolean canBuy = cost <= profile.getGold() && level<upgradeType.getMaxLevel();
            upgradeShopItems.add(new UpgradeShopItemDTO(upgradeType.getId(), upgradeType.getName()
                    , upgradeType.getDescription(), upgradeType.getEffectType(), upgradeType.getValue() * (level+1),
                    level, upgradeType.getMaxLevel(), cost, canBuy));
        }
        return upgradeShopItems;
    }
}
