package com.fazenda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsumoDAO extends BaseDAO {
    
    // Atualiza o estoque de insumos no rancho.
    public void atualizarEstoqueInsumos(int insumoId, int quantidade, int ranchoId) throws SQLException {
        String sql = "INSERT INTO estoque_insumos (fk_id_insumo, quantidade, fk_id_rancho) " +
                     "VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, insumoId);
            stmt.setInt(2, quantidade);
            stmt.setInt(3, ranchoId);
            stmt.setInt(4, quantidade);
            stmt.executeUpdate();
        }
    }
    
    // Opcional: método para consultar a quantidade atual de insumos no rancho
    public int getEstoqueInsumos(int insumoId, int ranchoId) throws SQLException {
        String sql = "SELECT quantidade FROM estoque_insumos WHERE fk_id_insumo = ? AND fk_id_rancho = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, insumoId);
            stmt.setInt(2, ranchoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantidade");
                }
            }
        }
        return 0;
    }
}
