
package com.solvd.project.service.mybatisiml;

import com.solvd.project.dao.PolicyDAO;
import com.solvd.project.dao.interfaces.PolicyDAOI;
import org.apache.ibatis.session.SqlSession;

import com.solvd.project.model.Policy;
import com.solvd.project.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PolicyServiceMyBatis {
    private static final org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = MyBatisUtil
            .getSqlSessionFactory();

    public PolicyServiceMyBatis(PolicyDAO ignored) {
        // DAO is ignored, just for compatibility with Main
    }

    public java.util.List<com.solvd.project.model.Policy> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyDAOI dao = session.getMapper(PolicyDAOI.class);
            return dao.getAll();
        }
    }

    public Policy get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyDAOI dao = session.getMapper(PolicyDAOI.class);
            return dao.getById(id);
        }
    }

    public void create(Policy policy) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyDAOI dao = session.getMapper(PolicyDAOI.class);
            dao.insert(policy);
            session.commit();
        }
    }

    public void update(Policy policy) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyDAOI dao = session.getMapper(PolicyDAOI.class);
            dao.update(policy);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PolicyDAOI dao = session.getMapper(PolicyDAOI.class);
            dao.delete(id);
            session.commit();
        }
    }
}
