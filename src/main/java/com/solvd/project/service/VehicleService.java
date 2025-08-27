package com.solvd.project.service;

import com.solvd.project.dao.interfaces.VehicleDAOI;
import com.solvd.project.model.Vehicles;

import java.util.List;

public class VehicleService {
    private final VehicleDAOI dao;

    public VehicleService(VehicleDAOI dao) {
        this.dao = dao;
    }

    public Vehicles get(int id) {
        return dao.getById(id);
    }

    public List<Vehicles> getAll() {
        return dao.getAll();
    }

    public void create(Vehicles vehicle) {
        dao.insert(vehicle);
    }

    public void update(Vehicles vehicle) {
        dao.update(vehicle);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}