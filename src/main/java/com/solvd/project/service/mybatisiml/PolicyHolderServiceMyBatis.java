
package com.solvd.project.service.mybatisiml;

import com.solvd.project.dao.PolicyHolderDAO;
import com.solvd.project.dao.interfaces.PolicyHolderDAOI;
import org.apache.ibatis.session.SqlSession;

import com.solvd.project.model.PolicyHolders;
import com.solvd.project.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PolicyHolderServiceMyBatis {
    private static final org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = MyBatisUtil
            .getSqlSessionFactory();

    public PolicyHolderServiceMyBatis(PolicyHolderDAO ignored) {
        // DAO is ignored, just for compatibility with Main
    }

    public java.util.List<com.solvd.project.model.PolicyHolders> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyHolderDAOI dao = session.getMapper(PolicyHolderDAOI.class);
            return dao.getAll();
        }
    }

    public PolicyHolders get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyHolderDAOI dao = session.getMapper(PolicyHolderDAOI.class);
            return dao.getById(id);
        }
    }

    public void create(PolicyHolders holder) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyHolderDAOI dao = session.getMapper(PolicyHolderDAOI.class);
            dao.insert(holder);
            session.commit();
        }
    }

    public void update(PolicyHolders holder) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyHolderDAOI dao = session.getMapper(PolicyHolderDAOI.class);
            dao.update(holder);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyHolderDAOI dao = session.getMapper(PolicyHolderDAOI.class);
            dao.delete(id);
            session.commit();
        }
    }
}
