package com.fazenda.dao;

import com.fazenda.model.TiposAnimais;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoAnimalDAO extends BaseDAO {

    public List<TiposAnimais> listarTodosTipos() throws SQLException {
        List<TiposAnimais> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tiposAnimais";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TiposAnimais tipo = new TiposAnimais();
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                tipo.setTipoAlimentacao(rs.getString("tipo_alimentacao"));
                tipo.setDescricao(rs.getString("descricao"));
                tipos.add(tipo);
            }
        }
        return tipos;
    }
}