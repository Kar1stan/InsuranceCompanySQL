package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.PolicyDAOI;
import com.solvd.project.model.Policy;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDAO implements PolicyDAOI {
    Connection conn;

    public PolicyDAO() {
        try {
            this.conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public PolicyDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Policy getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Policies WHERE PolicyId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                        rs.getInt("PolicyId"),
                        rs.getString("Type"),
                        rs.getInt("Coverage"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getDate("EndDate").toLocalDate(),
                        rs.getString("Status"));
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
    public List<Policy> getAll() {
        List<Policy> policies = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Policies")) {
            while (rs.next()) {
                policies.add(new Policy(
                        rs.getInt("PolicyId"),
                        rs.getString("Type"),
                        rs.getInt("Coverage"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getDate("EndDate").toLocalDate(),
                        rs.getString("Status")));
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
        return policies;
    }

    @Override
    public void insert(Policy policy) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Policies (Type, Coverage, StartDate, EndDate, Status) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, policy.getType());
            stmt.setInt(2, policy.getCoverage());
            stmt.setDate(3, Date.valueOf(policy.getStartDate()));
            stmt.setDate(4, Date.valueOf(policy.getEndDate()));
            stmt.setString(5, policy.getStatus());
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
    public void update(Policy policy) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Policies SET Type = ?, Coverage = ?, StartDate = ?, EndDate = ?, Status = ? WHERE PolicyId = ?")) {
            stmt.setString(1, policy.getType());
            stmt.setInt(2, policy.getCoverage());
            stmt.setDate(3, Date.valueOf(policy.getStartDate()));
            stmt.setDate(4, Date.valueOf(policy.getEndDate()));
            stmt.setString(5, policy.getStatus());
            stmt.setInt(6, policy.getPolicyId());
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
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Policies WHERE PolicyId = ?")) {
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
