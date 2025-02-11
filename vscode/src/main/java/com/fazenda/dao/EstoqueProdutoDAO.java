package com.fazenda.dao;

import com.fazenda.model.ItemLoja;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueProdutoDAO extends BaseDAO {

    // Lista os produtos que o fazendeiro possui (join entre estoque_produtos e produtoAnimais)
    public List<ItemLoja> listarProdutosFazendeiro(int fazendeiroId) throws SQLException {
        List<ItemLoja> produtos = new ArrayList<>();
        String sql = "SELECT p.nome, p.preco_unitario, ep.quantidade AS quantidade_disponivel, p.id AS produtoId " +
                     "FROM estoque_produtos ep " +
                     "JOIN produtoAnimais p ON ep.fk_id_produto = p.id " +
                     "WHERE ep.fk_id_fazendeiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fazendeiroId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemLoja item = new ItemLoja();
                    item.setNome(rs.getString("nome"));
                    item.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                    item.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                    item.setTipoInsumoId(rs.getInt("produtoId"));
                    produtos.add(item);
                }
            }
        }
        return produtos;
    }

    // Atualiza o estoque do fazendeiro, diminuindo a quantidade vendida
    public boolean atualizarEstoqueProduto(int produtoId, int quantidade, int fazendeiroId) throws SQLException {
        // Primeiro, verifica se há quantidade suficiente
        String sqlSelect = "SELECT quantidade FROM estoque_produtos WHERE fk_id_produto = ? AND fk_id_fazendeiro = ?";
        try (PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, produtoId);
            stmtSelect.setInt(2, fazendeiroId);
            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next()) {
                    int quantidadeDisponivel = rs.getInt("quantidade");
                    if (quantidadeDisponivel < quantidade) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        // Se houver estoque suficiente, atualiza
        String sqlUpdate = "UPDATE estoque_produtos SET quantidade = quantidade - ? " +
                           "WHERE fk_id_produto = ? AND fk_id_fazendeiro = ?";
        try (PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate)) {
            stmtUpdate.setInt(1, quantidade);
            stmtUpdate.setInt(2, produtoId);
            stmtUpdate.setInt(3, fazendeiroId);
            int rows = stmtUpdate.executeUpdate();
            return rows > 0;
        }
    }
}
