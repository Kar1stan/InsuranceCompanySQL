package com.solvd.project.dao.interfaces;

import com.solvd.project.model.PolicyHolders;
import java.util.List;

public interface PolicyHolderDAOI {
    PolicyHolders getById(int id);

    List<PolicyHolders> getAll();

    void insert(PolicyHolders holder);

    void update(PolicyHolders holder);

    void delete(int id);
}
