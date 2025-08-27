package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Policy;
import java.util.List;

public interface PolicyDAOI {
    Policy getById(int id);

    List<Policy> getAll();

    void insert(Policy policy);

    void update(Policy policy);

    void delete(int id);
}
