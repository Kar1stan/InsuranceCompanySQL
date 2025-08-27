package com.solvd.project.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Adjuster")
public class AdjusterJAXB {

    @XmlElement(name = "idAdjusters")
    private int id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Contact")
    private String contact;

    @XmlElement(name = "Assigned_Case")
    private String assignedCase;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAssignedCase() {
        return assignedCase;
    }

    public void setAssignedCase(String assignedCase) {
        this.assignedCase = assignedCase;
    }
}
