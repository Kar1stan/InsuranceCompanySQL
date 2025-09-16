package com.solvd.project.model.strategy;

import com.solvd.project.model.Claims;

public class FlaggedByAdjusterStrategy implements FraudDetectionStrategy {
    @Override
    public boolean isFraudulent(Claims claim) {
        return "adjuster".equalsIgnoreCase(claim.getFlaggedBy());
    }
}
