package com.solvd.project.dao.interfaces;

import com.solvd.project.model.Accidents;
import java.util.List;

public interface AccidentDAOI {
    Accidents getById(int id);

    List<Accidents> getAll();

    void insert(Accidents accident);

    void update(Accidents accident);

    void delete(int id);
}
