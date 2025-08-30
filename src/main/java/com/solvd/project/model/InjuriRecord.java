package com.solvd.project.model;

public class InjuriRecord {
    private int idInjuriRecords;
    private String type;
    private String severity;
    private String hospitalInfo;
    private String treatment;
    private int idAccidents;

    // Getters and setters
    public int getIdInjuriRecords() {
        return idInjuriRecords;
    }

    public void setIdInjuriRecords(int id) {
        this.idInjuriRecords = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getHospitalInfo() {
        return hospitalInfo;
    }

    public void setHospitalInfo(String hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getIdAccidents() {
        return idAccidents;
    }

    public void setIdAccidents(int idAccidents) {
        this.idAccidents = idAccidents;
    }
}
