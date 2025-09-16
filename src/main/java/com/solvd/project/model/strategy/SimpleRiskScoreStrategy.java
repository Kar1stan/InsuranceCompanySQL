package com.solvd.project.model.strategy;

import com.solvd.project.model.Claims;

public class SimpleRiskScoreStrategy implements FraudDetectionStrategy {
    @Override
    public boolean isFraudulent(Claims claim) {
        return claim.getAmount() > 10000;
    }
}
