package com.fazenda.dao;

import com.fazenda.util.DatabaseConnection;
import java.sql.*;

public abstract class BaseDAO {
    protected Connection connection;

    public BaseDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    protected void closeResources(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}
