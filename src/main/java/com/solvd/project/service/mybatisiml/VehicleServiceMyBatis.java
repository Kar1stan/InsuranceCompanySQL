
package com.solvd.project.service.mybatisiml;

import com.solvd.project.dao.VehicleDAO;
import com.solvd.project.dao.interfaces.VehicleDAOI;
import org.apache.ibatis.session.SqlSession;

import com.solvd.project.model.Vehicles;
import com.solvd.project.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class VehicleServiceMyBatis {
    private static final org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = MyBatisUtil
            .getSqlSessionFactory();

    public VehicleServiceMyBatis(VehicleDAO ignored) {
        // DAO is ignored, just for compatibility with Main
    }

    public java.util.List<com.solvd.project.model.Vehicles> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            VehicleDAOI dao = session.getMapper(VehicleDAOI.class);
            return dao.getAll();
        }
    }

    public Vehicles get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            VehicleDAOI dao = session.getMapper(VehicleDAOI.class);
            return dao.getById(id);
        }
    }

    public void create(Vehicles vehicle) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            VehicleDAOI dao = session.getMapper(VehicleDAOI.class);
            dao.insert(vehicle);
            session.commit();
        }
    }

    public void update(Vehicles vehicle) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            VehicleDAOI dao = session.getMapper(VehicleDAOI.class);
            dao.update(vehicle);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            VehicleDAOI dao = session.getMapper(VehicleDAOI.class);
            dao.delete(id);
            session.commit();
        }
    }
}
