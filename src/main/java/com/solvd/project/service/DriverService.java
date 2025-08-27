package com.solvd.project.service;

import com.solvd.project.dao.interfaces.DriverDAOI;
import com.solvd.project.model.Drivers;

import java.util.List;

public class DriverService {
    private final DriverDAOI dao;

    public DriverService(DriverDAOI dao) {
        this.dao = dao;
    }

    public Drivers get(int id) {
        return dao.getById(id);
    }

    public List<Drivers> getAll() {
        return dao.getAll();
    }

    public void create(Drivers driver) {
        dao.insert(driver);
    }

    public void update(Drivers driver) {
        dao.update(driver);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}