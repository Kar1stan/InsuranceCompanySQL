package com.solvd.project.dao;

import com.solvd.project.dao.interfaces.PolicyHolderDAOI;
import com.solvd.project.model.PolicyHolders;
import com.solvd.project.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyHolderDAO implements PolicyHolderDAOI {
    Connection conn;

    {
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public PolicyHolderDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public PolicyHolders getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PolicyHolders WHERE PolicyHoldersId = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PolicyHolders(
                        rs.getInt("PolicyHoldersId"),
                        rs.getString("Name"),
                        rs.getString("Contact"),
                        rs.getDate("DOB").toLocalDate());
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
    public List<PolicyHolders> getAll() {
        List<PolicyHolders> holders = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM PolicyHolders")) {
            while (rs.next()) {
                holders.add(new PolicyHolders(
                        rs.getInt("PolicyHoldersId"),
                        rs.getString("Name"),
                        rs.getString("Contact"),
                        rs.getDate("DOB").toLocalDate()));
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
        return holders;
    }

    @Override
    public void insert(PolicyHolders holder) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PolicyHolders (Name, Contact, DOB) VALUES (?, ?, ?)")) {
            stmt.setString(1, holder.getName());
            stmt.setString(2, holder.getContact());
            stmt.setDate(3, Date.valueOf(holder.getDob()));
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
    public void update(PolicyHolders holder) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE PolicyHolders SET Name = ?, Contact = ?, DOB = ? WHERE PolicyHoldersId = ?")) {
            stmt.setString(1, holder.getName());
            stmt.setString(2, holder.getContact());
            stmt.setDate(3, Date.valueOf(holder.getDob()));
            stmt.setInt(4, holder.getPolicyHolderId());
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
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM PolicyHolders WHERE PolicyHoldersId = ?")) {
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