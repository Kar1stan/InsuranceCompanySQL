package com.solvd.project.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Witness")
public class WitnessJAXB {

    @XmlElement(name = "idWitnesses")
    private int idWitnesses;

    @XmlElement(name = "Contact_Info")
    private String contactInfo;

    @XmlElement(name = "Statement_Summary")
    private String statementSummary;

    // Getters and setters
    public int getIdWitnesses() {
        return idWitnesses;
    }

    public void setIdWitnesses(int idWitnesses) {
        this.idWitnesses = idWitnesses;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatementSummary() {
        return statementSummary;
    }

    public void setStatementSummary(String statementSummary) {
        this.statementSummary = statementSummary;
    }
}
