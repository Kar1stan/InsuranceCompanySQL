package com.solvd.project.service;

import com.solvd.project.dao.interfaces.DriverDAOI;
import com.solvd.project.model.Drivers;

import java.util.List;

public class DriverService {
    private final DriverDAOI dao;

    public DriverService() {
        this.dao = new com.solvd.project.dao.DriverDAO();
    }

    public DriverService(com.solvd.project.dao.DriverDAO dao) {
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