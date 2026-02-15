package com.stefan.survivorgamebackend.controller;

import com.stefan.survivorgamebackend.dto.BuyUpgradeResponse;
import com.stefan.survivorgamebackend.dto.PlayerModifiers;
import com.stefan.survivorgamebackend.service.UpgradeEffectService;
import com.stefan.survivorgamebackend.service.UpgradeService;
import com.stefan.survivorgamebackend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/player")
@AllArgsConstructor
public class PlayerController {
    private final UpgradeEffectService upgradeEffectService;
    private final UserProfileService userProfileService;
    private final UpgradeService upgradeService;

    @GetMapping("/modifiers")
    public ResponseEntity<PlayerModifiers> getMyModifiers() {
        return ResponseEntity.ok(upgradeEffectService.calculateModifiers(userProfileService.getCurrentProfile()));
    }

    @PostMapping("/upgrades/{upgradeId}/buy")
    public ResponseEntity<BuyUpgradeResponse> buyUpgrade(@PathVariable UUID upgradeId) {
        return ResponseEntity.ok(upgradeService.buyUpgrade(upgradeId));
    }
}