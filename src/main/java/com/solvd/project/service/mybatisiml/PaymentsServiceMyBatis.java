
package com.solvd.project.service.mybatisiml;

import com.solvd.project.dao.PaymentDAO;
import com.solvd.project.dao.interfaces.PaymentsDAOI;
import org.apache.ibatis.session.SqlSession;

import com.solvd.project.model.Payments;
import com.solvd.project.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PaymentsServiceMyBatis {
    private static final org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = MyBatisUtil
            .getSqlSessionFactory();

    public PaymentsServiceMyBatis(PaymentDAO ignored) {
        // DAO is ignored, just for compatibility with Main
    }

    public java.util.List<com.solvd.project.model.Payments> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PaymentsDAOI dao = session.getMapper(PaymentsDAOI.class);
            return dao.getAll();
        }
    }

    public Payments get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PaymentsDAOI dao = session.getMapper(PaymentsDAOI.class);
            return dao.getById(id);
        }
    }

    public void create(Payments payment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PaymentsDAOI dao = session.getMapper(PaymentsDAOI.class);
            dao.insert(payment);
            session.commit();
        }
    }

    public void update(Payments payment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PaymentsDAOI dao = session.getMapper(PaymentsDAOI.class);
            dao.update(payment);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PaymentsDAOI dao = session.getMapper(PaymentsDAOI.class);
            dao.delete(id);
            session.commit();
        }
    }
}
