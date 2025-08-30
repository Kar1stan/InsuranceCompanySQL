package com.solvd.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.project.model.FraudCheck;
import com.solvd.project.model.InjuriRecord;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JsonInsuranceService {
    private List<FraudCheck> fraudChecks;
    private List<InjuriRecord> injuriRecords;

    public JsonInsuranceService(String jsonPath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> data = mapper.readValue(new File(jsonPath), Map.class);

            fraudChecks = mapper.convertValue(data.get("fraudChecks"),
                    mapper.getTypeFactory().constructCollectionType(List.class, FraudCheck.class));

            injuriRecords = mapper.convertValue(data.get("injuriRecords"),
                    mapper.getTypeFactory().constructCollectionType(List.class, InjuriRecord.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FraudCheck> getFraudChecks() {
        return fraudChecks;
    }

    public List<InjuriRecord> getInjuriRecords() {
        return injuriRecords;
    }
}
