package com.solvd.project.model.strategy;

import com.solvd.project.model.Claims;

public class SimpleRiskScoreStrategy implements FraudDetectionStrategy {
    @Override
    public boolean isFraudulent(Claims claim) {
        // Example: flag as fraud if claim amount > 10000
        return claim.getAmount() > 10000;
    }
}
