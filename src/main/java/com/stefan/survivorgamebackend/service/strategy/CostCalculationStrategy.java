package com.stefan.survivorgamebackend.service.strategy;

import com.stefan.survivorgamebackend.model.ScalingType;

public interface CostCalculationStrategy {
    long calculateCost(long baseCost, int level, double scalingFactor);
    ScalingType getType();
}
