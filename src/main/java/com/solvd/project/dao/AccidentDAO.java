package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.AccidentDAOI;
import com.solvd.project.model.Accidents;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccidentDAO implements AccidentDAOI {
    private final Connection conn;

    public AccidentDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Accidents getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Accidents WHERE idAccidents = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Accidents(
                        rs.getInt("idAccidents"),
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Severity"),
                        rs.getString("Location"),
                        rs.getString("Weather"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Accidents> getAll() {
        List<Accidents> accidents = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Accidents")) {
            while (rs.next()) {
                accidents.add(new Accidents(
                        rs.getInt("idAccidents"),
                        rs.getTimestamp("Date").toLocalDateTime(),
                        rs.getString("Severity"),
                        rs.getString("Location"),
                        rs.getString("Weather")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accidents;
    }

    @Override
    public void insert(Accidents accident) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Accidents (Date, Severity, Location, Weather) VALUES (?, ?, ?, ?)")) {
            stmt.setTimestamp(1, Timestamp.valueOf(accident.getDate()));
            stmt.setString(2, accident.getSeverity());
            stmt.setString(3, accident.getLocation());
            stmt.setString(4, accident.getWeather());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Accidents accident) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Accidents SET Date = ?, Severity = ?, Location = ?, Weather = ? WHERE idAccidents = ?")) {
            stmt.setTimestamp(1, Timestamp.valueOf(accident.getDate()));
            stmt.setString(2, accident.getSeverity());
            stmt.setString(3, accident.getLocation());
            stmt.setString(4, accident.getWeather());
            stmt.setInt(5, accident.getIdAccidents());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Accidents WHERE idAccidents = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
