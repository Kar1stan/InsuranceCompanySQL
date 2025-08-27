package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Vehicles;
import java.util.List;

public interface VehicleDAOI {
    Vehicles getById(int id);

    List<Vehicles> getAll();

    void insert(Vehicles vehicle);

    void update(Vehicles vehicle);

    void delete(int id);
}
