package com.stefan.survivorgamebackend.dto;

public record PlayerModifiers(double goldMultiplier, double xpMultiplier,
                              double damageMultiplier, double startHpBonus,
                              int revives, double luckMultiplier, double speedBonus) {
}
