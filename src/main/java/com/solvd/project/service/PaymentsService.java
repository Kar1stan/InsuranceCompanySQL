package com.solvd.project.service;

import com.solvd.project.dao.interfaces.PaymentsDAOI;
import com.solvd.project.model.Payments;

import java.util.List;

public class PaymentsService {
    private final PaymentsDAOI dao;

    public PaymentsService(PaymentsDAOI dao) {
        this.dao = dao;
    }

    public Payments get(int id) {
        return dao.getById(id);
    }

    public List<Payments> getAll() {
        return dao.getAll();
    }

    public void create(Payments payment) {
        dao.insert(payment);
    }

    public void update(Payments payment) {
        dao.update(payment);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}
