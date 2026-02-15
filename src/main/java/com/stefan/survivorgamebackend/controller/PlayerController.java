package com.stefan.survivorgamebackend.controller;

import com.stefan.survivorgamebackend.dto.PlayerModifiers;
import com.stefan.survivorgamebackend.service.UpgradeEffectService;
import com.stefan.survivorgamebackend.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
@AllArgsConstructor
public class PlayerController {
    private final UpgradeEffectService upgradeEffectService;
    private final UserProfileService userProfileService;

    @GetMapping("/modifiers")
    public ResponseEntity<PlayerModifiers> getMyModifiers() {
        return ResponseEntity.ok(upgradeEffectService.calculateModifiers(userProfileService.getCurrentProfile()));
    }
}