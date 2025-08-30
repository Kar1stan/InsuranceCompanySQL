package com.solvd.project.model;

public class FraudCheck {
    private int idFraudChecks;
    private String flaggedBy;
    private String flagReason;
    private double riskScore;
    private String dateFlagged;
    private String dateResolved;

    // Getters and setters
    public int getIdFraudChecks() {
        return idFraudChecks;
    }

    public void setIdFraudChecks(int id) {
        this.idFraudChecks = id;
    }

    public String getFlaggedBy() {
        return flaggedBy;
    }

    public void setFlaggedBy(String flaggedBy) {
        this.flaggedBy = flaggedBy;
    }

    public String getFlagReason() {
        return flagReason;
    }

    public void setFlagReason(String flagReason) {
        this.flagReason = flagReason;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getDateFlagged() {
        return dateFlagged;
    }

    public void setDateFlagged(String dateFlagged) {
        this.dateFlagged = dateFlagged;
    }

    public String getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(String dateResolved) {
        this.dateResolved = dateResolved;
    }
}
