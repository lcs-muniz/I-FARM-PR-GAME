package com.fazenda.dao;

import com.fazenda.model.Fazendeiro;
import java.math.BigDecimal;
import java.sql.*;

public class FazendeiroDAO extends BaseDAO {
    public int criarFazendeiro(Fazendeiro fazendeiro) throws SQLException {
        String sql = "INSERT INTO fazendeiro (nome, sexo, idade, saldo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fazendeiro.getNome());
            stmt.setString(2, String.valueOf(fazendeiro.getSexo()));
            stmt.setInt(3, fazendeiro.getIdade());
            stmt.setBigDecimal(4, fazendeiro.getSaldo());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    fazendeiro.setId(id);
                    return id;
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o fazendeiro.");
                }
            }
        }
    }

    public BigDecimal getSaldo(int fazendeiroId) throws SQLException {
        String sql = "SELECT saldo FROM fazendeiro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fazendeiroId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    return rs.getBigDecimal("saldo");
                }
            }
        }
        return BigDecimal.ZERO;
    }

    public String getNome(int fazendeiroId) throws SQLException {
        String sql = "SELECT nome FROM fazendeiro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fazendeiroId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    return rs.getString("nome");
                }
            }
        }
        return "";
    }

    public void atualizarSaldo(int fazendeiroId, BigDecimal valor) throws SQLException {
        String sql = "UPDATE fazendeiro SET saldo = saldo + ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, valor);
            stmt.setInt(2, fazendeiroId);
            stmt.executeUpdate();
        }
    }
}
