package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Claims;
import java.util.List;

public interface ClaimDAOI {
    Claims getById(int id);

    List<Claims> getAll();

    void insert(Claims claim);

    void update(Claims claim);

    void delete(int id);
}
