package com.solvd.project.model.observer;

import com.solvd.project.model.Claims;
import java.util.ArrayList;
import java.util.List;

public class ClaimSubject {
    private final List<ClaimObserver> observers = new ArrayList<>();

    public void addObserver(ClaimObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ClaimObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Claims claim) {
        for (ClaimObserver observer : observers) {
            observer.onClaimUpdated(claim);
        }
    }
}
