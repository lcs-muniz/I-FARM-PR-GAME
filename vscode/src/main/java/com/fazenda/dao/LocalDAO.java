package com.fazenda.dao;

import com.fazenda.model.Locais;
import java.sql.*;

public class LocalDAO extends BaseDAO {
    public void criarLocal(Locais local) throws SQLException {
        String sql = "INSERT INTO locais (capacidade, tipo_alimentacao, nivel, tipo_local, fk_id_rancho) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, local.getCapacidade());
            stmt.setString(2, local.getTipoAlimentacao());
            stmt.setInt(3, local.getNivel());
            stmt.setString(4, local.getTipoLocal().name());
            stmt.setInt(5, local.getFkIdRancho());
            stmt.executeUpdate();
        }
    }
    
    // Método para criar locais iniciais para um rancho
    public void criarLocaisIniciais(int ranchoId) throws SQLException {
        String[] tipos = {"Galinheiro", "Pasto", "Aprisco"};
        String sql = "INSERT INTO locais (capacidade, tipo_alimentacao, nivel, tipo_local, fk_id_rancho) VALUES (10, 'Grãos', 1, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String tipo : tipos) {
                stmt.setString(1, tipo);
                stmt.setInt(2, ranchoId);
                stmt.executeUpdate();
            }
        }
    }
}
