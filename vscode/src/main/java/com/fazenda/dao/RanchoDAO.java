package com.fazenda.dao;

import com.fazenda.model.Rancho;
import java.sql.*;

public class RanchoDAO extends BaseDAO {
    public int criarRancho(Rancho rancho) throws SQLException {
        String sql = "INSERT INTO rancho (nome, nivel, fk_id_fazendeiro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, rancho.getNome());
            stmt.setInt(2, rancho.getNivel());
            if (rancho.getFkIdFazendeiro() != null) {
                stmt.setInt(3, rancho.getFkIdFazendeiro());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()){
                    int id = rs.getInt(1);
                    rancho.setId(id);
                    return id;
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o rancho.");
                }
            }
        }
    }
}
