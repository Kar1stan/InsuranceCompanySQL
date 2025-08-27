package com.solvd.project.dao.interfaces;

import com.solvd.project.model.WeatherConditions;
import java.util.List;

public interface WeatherConditionsDAOI {
    WeatherConditions getById(int id);

    List<WeatherConditions> getAll();

    void insert(WeatherConditions weather);

    void update(WeatherConditions weather);

    void delete(int id);
}
