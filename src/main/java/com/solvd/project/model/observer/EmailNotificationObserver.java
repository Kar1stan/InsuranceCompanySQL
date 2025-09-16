package com.solvd.project.model.observer;

import com.solvd.project.model.Claims;

public class EmailNotificationObserver implements ClaimObserver {
    @Override
    public void onClaimUpdated(Claims claim) {
        System.out.println("Email sent for claim update: " + claim.getClaimId());
    }
}
