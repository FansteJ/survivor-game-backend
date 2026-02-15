package com.stefan.survivorgamebackend.dto;

import com.stefan.survivorgamebackend.model.EffectType;

import java.util.UUID;

public record UpgradeShopItemDTO(UUID upgradeTypeId, String name,
                                 String description, EffectType effectType, double value,
                                 int level, int maxLevel, long cost, boolean canBuy) {
}
