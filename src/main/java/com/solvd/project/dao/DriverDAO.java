   package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.DriverDAOI;
import com.solvd.project.model.Drivers;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO implements DriverDAOI {
    Connection conn;

    public DriverDAO() {
        try {
            this.conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public DriverDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Drivers getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Drivers WHERE DriverId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Drivers(
                        rs.getInt("DriverId"),
                        rs.getString("License"),
                        rs.getString("Experience"));
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
    public List<Drivers> getAll() {
        List<Drivers> drivers = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Drivers")) {
            while (rs.next()) {
                drivers.add(new Drivers(
                        rs.getInt("DriverId"),
                        rs.getString("License"),
                        rs.getString("Experience")));
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
        return drivers;
    }

    @Override
    public void insert(Drivers driver) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Drivers (License, Experience) VALUES (?, ?)")) {
            stmt.setString(1, driver.getLicense());
            stmt.setString(2, driver.getExperience());
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
    public void update(Drivers driver) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Drivers SET License = ?, Experience = ? WHERE DriverId = ?")) {
            stmt.setString(1, driver.getLicense());
            stmt.setString(2, driver.getExperience());
            stmt.setInt(3, driver.getDriverId());
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
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Drivers WHERE DriverId = ?")) {
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