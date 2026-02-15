package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.UpgradeShopItemDTO;
import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import com.stefan.survivorgamebackend.repository.UpgradeTypeRepository;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import com.stefan.survivorgamebackend.repository.UserUpgradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UpgradeTypeRepository upgradeTypeRepository;
    private final UserUpgradeRepository userUpgradeRepository;
    private final UpgradeService upgradeService;

    public List<UpgradeShopItemDTO> getShopItems() {
        User currentUser = userRepository.findByUsername(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("User not found"));
        UserProfile profile = userProfileRepository.findByUser(currentUser).orElseThrow(()
                -> new RuntimeException("User profile not found"));
        List<UpgradeType> upgradeTypes = upgradeTypeRepository.findAll();
        List<UpgradeShopItemDTO> upgradeShopItems = new ArrayList<>();
        for(UpgradeType upgradeType : upgradeTypes) {
            UserUpgrade upgrade = userUpgradeRepository.findByUserProfileAndUpgradeType(profile, upgradeType).orElse(null);
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
                    level, upgradeType.getMaxLevel(), cost, (cost<=profile.getGold())));
        }
        return upgradeShopItems;
    }
}
