package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UpgradeType {
    @Id
    private String id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private EffectType effectType;

    @Enumerated(EnumType.STRING)
    private ScalingType scalingType;

    private double scalingFactor;

    private double value;
    private long baseCost;
    private int maxLevel;
}
