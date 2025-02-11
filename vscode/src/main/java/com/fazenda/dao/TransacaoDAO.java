package com.fazenda.dao;

import com.fazenda.model.Transacao;
import java.sql.*;

public class TransacaoDAO extends BaseDAO {
    public void registrarTransacao(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacao (data_transacao, tipo_transacao, quantidade, produto, valor_total, tipo_item, fk_id_animais, fk_id_rancho, fk_id_fazendeiro) "
                   + "VALUES (NOW(), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transacao.getTipoTransacao());
            stmt.setInt(2, transacao.getQuantidade());
            stmt.setString(3, transacao.getProduto());
            stmt.setBigDecimal(4, transacao.getValorTotal());
            stmt.setString(5, transacao.getTipoItem());
           
            if (transacao.getFkIdAnimais() == null) {
                stmt.setNull(6, Types.INTEGER);
            } else {
                stmt.setInt(6, transacao.getFkIdAnimais());
            }
            if (transacao.getFkIdRancho() == null) {
                stmt.setNull(7, Types.INTEGER);
            } else {
                stmt.setInt(7, transacao.getFkIdRancho());
            }
            stmt.setInt(8, transacao.getFkIdFazendeiro());
            stmt.executeUpdate();
        }
    }
}
