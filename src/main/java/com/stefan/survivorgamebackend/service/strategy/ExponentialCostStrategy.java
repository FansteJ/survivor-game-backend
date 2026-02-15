package com.stefan.survivorgamebackend.service.strategy;

import com.stefan.survivorgamebackend.model.ScalingType;
import org.springframework.stereotype.Component;

@Component
public class ExponentialCostStrategy implements CostCalculationStrategy {
    @Override
    public long calculateCost(long baseCost, int level, double scalingFactor) {
        return (long) (baseCost * Math.pow(scalingFactor, level));
    }

    @Override
    public ScalingType getType() {
        return null;
    }
}
