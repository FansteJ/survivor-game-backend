package com.stefan.survivorgamebackend.dto;

import com.stefan.survivorgamebackend.model.EffectType;

public record UpgradeShopItemDTO(String upgradeTypeId, String name,
                                 String description, EffectType effectType, double value,
                                 int level, int maxLevel, long cost, boolean canBuy) {
}
