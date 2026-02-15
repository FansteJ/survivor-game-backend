package com.stefan.survivorgamebackend.controller;

import com.stefan.survivorgamebackend.dto.BuyUpgradeResponse;
import com.stefan.survivorgamebackend.dto.PlayerModifiers;
import com.stefan.survivorgamebackend.dto.UpgradeShopItemDTO;
import com.stefan.survivorgamebackend.service.ShopService;
import com.stefan.survivorgamebackend.service.UpgradeEffectService;
import com.stefan.survivorgamebackend.service.UpgradeService;
import com.stefan.survivorgamebackend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/player")
@AllArgsConstructor
public class PlayerController {
    private final UpgradeEffectService upgradeEffectService;
    private final UserProfileService userProfileService;
    private final UpgradeService upgradeService;
    private final ShopService shopService;

    @GetMapping("/modifiers")
    public ResponseEntity<PlayerModifiers> getMyModifiers() {
        return ResponseEntity.ok(upgradeEffectService.calculateModifiers(userProfileService.getCurrentProfile()));
    }

    @GetMapping("/upgrades")
    public ResponseEntity<List<UpgradeShopItemDTO>> getShopItems() {
        return ResponseEntity.ok(shopService.getShopItems());
    }

    @PostMapping("/upgrades/{upgradeId}/buy")
    public ResponseEntity<BuyUpgradeResponse> buyUpgrade(@PathVariable UUID upgradeId) {
        return ResponseEntity.ok(upgradeService.buyUpgrade(upgradeId));
    }
}