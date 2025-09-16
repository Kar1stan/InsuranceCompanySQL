package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.PaymentsDAOI;
import com.solvd.project.model.Payments;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements PaymentsDAOI {
    Connection conn;

    public PaymentDAO() {
        try {
            this.conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Payments getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Payments WHERE PaymentId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payments(
                        rs.getInt("PaymentId"),
                        rs.getString("Method"),
                        rs.getDouble("Amount"),
                        rs.getDate("Date").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Payments> getAll() {
        List<Payments> payments = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Payments")) {
            while (rs.next()) {
                payments.add(new Payments(
                        rs.getInt("PaymentId"),
                        rs.getString("Method"),
                        rs.getDouble("Amount"),
                        rs.getDate("Date").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return payments;
    }

    @Override
    public void insert(Payments payment) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Payments (Method, Amount, Date) VALUES (?, ?, ?)")) {
            stmt.setString(1, payment.getMethod());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Payments payment) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Payments SET Method = ?, Amount = ?, Date = ? WHERE PaymentId = ?")) {
            stmt.setString(1, payment.getMethod());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setInt(4, payment.getPaymentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Payments WHERE PaymentId = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}