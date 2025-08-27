package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Payments;
import java.util.List;

public interface PaymentsDAOI {
    Payments getById(int id);

    List<Payments> getAll();

    void insert(Payments payment);

    void update(Payments payment);

    void delete(int id);
}
