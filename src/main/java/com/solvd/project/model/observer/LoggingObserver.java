package com.solvd.project.model.observer;

import com.solvd.project.model.Claims;

public class LoggingObserver implements ClaimObserver {
    @Override
    public void onClaimUpdated(Claims claim) {
        System.out.println("Claim updated: " + claim.getClaimId());
    }
}
