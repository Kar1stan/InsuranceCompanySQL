
package com.solvd.project.service.mybatisiml;

import com.solvd.project.dao.DriverDAO;
import com.solvd.project.dao.interfaces.DriverDAOI;
import org.apache.ibatis.session.SqlSession;

import com.solvd.project.model.Drivers;
import com.solvd.project.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DriverServiceMyBatis {
    private static final org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = MyBatisUtil
            .getSqlSessionFactory();

    public DriverServiceMyBatis(DriverDAO ignored) {
        // DAO is ignored, just for compatibility with Main
    }

    public java.util.List<com.solvd.project.model.Drivers> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DriverDAOI dao = session.getMapper(DriverDAOI.class);
            return dao.getAll();
        }
    }

    public Drivers get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DriverDAOI dao = session.getMapper(DriverDAOI.class);
            return dao.getById(id);
        }
    }

    public void create(Drivers driver) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DriverDAOI dao = session.getMapper(DriverDAOI.class);
            dao.insert(driver);
            session.commit();
        }
    }

    public void update(Drivers driver) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DriverDAOI dao = session.getMapper(DriverDAOI.class);
            dao.update(driver);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DriverDAOI dao = session.getMapper(DriverDAOI.class);
            dao.delete(id);
            session.commit();
        }
    }
}
