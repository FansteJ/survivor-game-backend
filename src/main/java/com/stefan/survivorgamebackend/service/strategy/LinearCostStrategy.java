package com.stefan.survivorgamebackend.service.strategy;

import com.stefan.survivorgamebackend.model.ScalingType;
import org.springframework.stereotype.Component;

@Component
public class LinearCostStrategy implements CostCalculationStrategy {
    @Override
    public long calculateCost(long baseCost, int level, double scalingFactor) {
        return (long) (baseCost + scalingFactor * level);
    }

    @Override
    public ScalingType getType() {
        return ScalingType.LINEAR;
    }
}
