package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.WeatherConditionsDAOI;
import com.solvd.project.model.WeatherConditions;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherConditionsDAO implements WeatherConditionsDAOI {
    Connection conn;

    {
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public WeatherConditionsDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public WeatherConditions getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WeatherConditions WHERE WeatherId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new WeatherConditions(
                        rs.getInt("WeatherId"),
                        rs.getString("Condition"),
                        rs.getDouble("Temperature"),
                        rs.getDate("ReportedDate").toLocalDate());
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
    public List<WeatherConditions> getAll() {
        List<WeatherConditions> conditions = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM WeatherConditions")) {
            while (rs.next()) {
                conditions.add(new WeatherConditions(
                        rs.getInt("WeatherId"),
                        rs.getString("Condition"),
                        rs.getDouble("Temperature"),
                        rs.getDate("ReportedDate").toLocalDate()));
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
        return conditions;
    }

    @Override
    public void insert(WeatherConditions wc) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO WeatherConditions (Condition, Temperature, ReportedDate) VALUES (?, ?, ?)")) {
            stmt.setString(1, wc.getCondition());
            stmt.setDouble(2, wc.getTemperature());
            stmt.setDate(3, Date.valueOf(wc.getDateReported()));
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
    public void update(WeatherConditions wc) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE WeatherConditions SET Condition = ?, Temperature = ?, ReportedDate = ? WHERE WeatherId = ?")) {
            stmt.setString(1, wc.getCondition());
            stmt.setDouble(2, wc.getTemperature());
            stmt.setDate(3, Date.valueOf(wc.getDateReported()));
            stmt.setInt(4, wc.getWeatherId());
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
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM WeatherConditions WHERE WeatherId = ?")) {
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