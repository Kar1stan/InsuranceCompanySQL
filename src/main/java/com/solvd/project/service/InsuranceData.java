package com.solvd.project.service;

import jakarta.xml.bind.annotation.*;
import java.util.List;

import com.solvd.project.model.AdjusterJAXB;
import com.solvd.project.model.WitnessJAXB;

@XmlRootElement(name = "InsuranceData")
@XmlAccessorType(XmlAccessType.FIELD)
public class InsuranceData {

    @XmlElement(name = "Witness")
    private List<WitnessJAXB> witnesses;

    @XmlElement(name = "Adjuster")
    private List<AdjusterJAXB> adjusters;

    public List<WitnessJAXB> getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(List<WitnessJAXB> witnesses) {
        this.witnesses = witnesses;
    }

    public List<AdjusterJAXB> getAdjusters() {
        return adjusters;
    }

    public void setAdjusters(List<AdjusterJAXB> adjusters) {
        this.adjusters = adjusters;
    }

}
