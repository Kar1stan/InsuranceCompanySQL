package com.solvd.project.model.strategy;

public interface FraudDetectionStrategy {
    boolean isFraudulent(com.solvd.project.model.Claims claim);
}
