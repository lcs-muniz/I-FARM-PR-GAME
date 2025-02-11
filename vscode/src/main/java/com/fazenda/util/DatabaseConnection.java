package com.fazenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// conexão com o banco de dados hardcoded para fins de demonstração

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/*****";
    private static final String USER = "*****";
    private static final String PASSWORD = "*****";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
