package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Drivers;
import java.util.List;

public interface DriverDAOI {
    Drivers getById(int id);

    List<Drivers> getAll();

    void insert(Drivers driver);

    void update(Drivers driver);

    void delete(int id);
}
