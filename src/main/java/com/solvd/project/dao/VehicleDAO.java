package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.VehicleDAOI;
import com.solvd.project.model.Vehicles;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements VehicleDAOI {
    Connection conn;

    {
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public VehicleDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Vehicles getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vehicles WHERE VehicleId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicles(
                        rs.getInt("VehicleId"),
                        rs.getString("Model"),
                        rs.getString("Registration_Year"),
                        rs.getString("VIN"));
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
    public List<Vehicles> getAll() {
        List<Vehicles> vehicles = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Vehicles")) {
            while (rs.next()) {
                vehicles.add(new Vehicles(
                        rs.getInt("VehicleId"),
                        rs.getString("Model"),
                        rs.getString("Registration_Year"),
                        rs.getString("VIN")));
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
        return vehicles;
    }

    @Override
    public void insert(Vehicles vehicle) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Vehicles (Model, Registration_Year, VIN) VALUES (?, ?, ?)")) {
            stmt.setString(1, vehicle.getModel());
            stmt.setString(2, vehicle.getRegistrationYear());
            stmt.setString(3, vehicle.getVin());
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
    public void update(Vehicles vehicle) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Vehicles SET Model = ?, Registration_Year = ?, VIN = ? WHERE VehicleId = ?")) {
            stmt.setString(1, vehicle.getModel());
            stmt.setString(2, vehicle.getRegistrationYear());
            stmt.setString(3, vehicle.getVin());
            stmt.setInt(4, vehicle.getVehicleId());
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
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vehicles WHERE VehicleId = ?")) {
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