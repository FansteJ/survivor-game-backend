package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.UpgradeShopItemDTO;
import com.stefan.survivorgamebackend.model.CurrencyType;
import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import com.stefan.survivorgamebackend.repository.UpgradeTypeRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

        Map<String, UserUpgrade> userUpgradeMap = userUpgrades.stream()
                .collect(Collectors.toMap(u -> u.getUpgradeType().getId(), u -> u));

        List<UpgradeShopItemDTO> upgradeShopItems = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");

        for(UpgradeType upgradeType : upgradeTypes) {
            UserUpgrade upgrade = userUpgradeMap.get(upgradeType.getId());
            int level;
            if(upgrade != null) {
                level = upgrade.getLevel();
            } else{
                level = 0;
            }
            long cost = upgradeService.calculateCost(upgradeType, level);
            boolean hasEnoughCurrency = upgradeType.getCurrencyType() == CurrencyType.GOLD
                    ? profile.getGold() >= cost
                    : profile.getGems() >= cost;

            boolean canBuy = hasEnoughCurrency && level < upgradeType.getMaxLevel();
            double currentVal = upgradeType.getValue() * level;
            double nextVal = upgradeType.getValue() * (level + 1);
            String description = (level == upgradeType.getMaxLevel())
                    ? String.format("%s: %s", upgradeType.getDescription(), df.format(currentVal))
                    : String.format("%s: %s -> %s", upgradeType.getDescription(), df.format(currentVal), df.format(nextVal));
            upgradeShopItems.add(new UpgradeShopItemDTO(upgradeType.getId(), upgradeType.getName()
                    , description, upgradeType.getEffectType(),
                    upgradeType.getCurrencyType(), nextVal,
                    level, upgradeType.getMaxLevel(), cost, canBuy));
        }
        return upgradeShopItems;
    }
}
