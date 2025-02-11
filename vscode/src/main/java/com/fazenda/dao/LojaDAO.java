package com.fazenda.dao;

import com.fazenda.model.ItemLoja;
import com.fazenda.model.LojaAnimais;
import com.fazenda.model.Produto;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LojaDAO extends BaseDAO {

    // Lista os animais disponíveis na loja e faz o join com tiposAnimais para obter o nome do tipo.
    public List<LojaAnimais> listarAnimaisDisponiveis() throws SQLException {
        List<LojaAnimais> animais = new ArrayList<>();
        String sql = "SELECT ta.id AS id, ta.nome, la.preco_unitario, la.quantidade_disponivel " +
                     "FROM loja_animais la " +
                     "JOIN tiposAnimais ta ON la.fk_id_tipo_animal = ta.id";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                LojaAnimais animal = new LojaAnimais();
                // Certifique-se de que sua classe LojaAnimais possua um atributo 'id'
                animal.setFkIdTipoAnimal(rs.getInt("id")); 
                animal.setNomeAnimal(rs.getString("nome"));
                animal.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                animal.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                animais.add(animal);
            }
        }
        return animais;
    }
    
    
    
    // Atualiza o estoque da loja para um determinado tipo de animal...
    public void atualizarEstoqueLoja(int tipoAnimalId, int quantidade) throws SQLException {
        String updateLoja = "UPDATE loja_animais SET quantidade_disponivel = quantidade_disponivel - ? WHERE fk_id_tipo_animal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateLoja)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, tipoAnimalId);
            stmt.executeUpdate();
        }
    }
    
    // Método para consultar os dados de um item da loja por nome do animal...
    // ItemLoja é uma classe auxiliar presente no model.
    public ItemLoja consultarItemPorNome(String animalNome) throws SQLException {
        String sql = "SELECT la.preco_unitario, la.quantidade_disponivel, ta.id AS tipoAnimalId " +
                     "FROM loja_animais la " +
                     "JOIN tiposAnimais ta ON la.fk_id_tipo_animal = ta.id " +
                     "WHERE ta.nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animalNome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItemLoja item = new ItemLoja();
                    item.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                    item.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                    item.setTipoAnimalId(rs.getInt("tipoAnimalId"));
                    return item;
                }
            }
        }
        return null;
    }

    // Lista os insumos disponíveis na loja: junta a tabela loja_insumos com a tabela insumos.
    public List<ItemLoja> listarInsumosDisponiveis() throws SQLException {
        List<ItemLoja> insumos = new ArrayList<>();
        String sql = "SELECT ins.nome, ins.marca, li.preco_unitario, li.quantidade_disponivel, ins.id AS insumoId " +
                     "FROM loja_insumos li " +
                     "JOIN insumos ins ON li.fk_id_insumo = ins.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItemLoja item = new ItemLoja();
                // Atribui os valores para os campos nome e marca
                item.setNome(rs.getString("nome"));
                item.setMarca(rs.getString("marca"));
                item.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                item.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                item.setTipoInsumoId(rs.getInt("insumoId"));
                insumos.add(item);
            }
        }
        return insumos;
    }

    // Atualiza o estoque da loja para um insumo
    public void atualizarEstoqueInsumos(int insumoId, int quantidade) throws SQLException {
        String sql = "UPDATE loja_insumos SET quantidade_disponivel = quantidade_disponivel - ? WHERE fk_id_insumo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, insumoId);
            stmt.executeUpdate();
        }
    }

    // Consulta os dados de um insumo na loja por nome
    public ItemLoja consultarInsumoPorNome(String insumoNome) throws SQLException {
        String sql = "SELECT li.preco_unitario, li.quantidade_disponivel, ins.id AS insumoId " +
                     "FROM loja_insumos li " +
                     "JOIN insumos ins ON li.fk_id_insumo = ins.id " +
                     "WHERE ins.nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, insumoNome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItemLoja item = new ItemLoja();
                    item.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                    item.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                    item.setTipoInsumoId(rs.getInt("insumoId"));
                    return item;
                }
            }
        }
    return null;
    }

    // Métodos para Produtos (venda)
    public List<Produto> listarProdutosDisponiveis() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT nome, preco_unitario FROM produtoAnimais";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                produtos.add(produto);
            }
        }
        return produtos;
    }

    // Consulta o preço unitário de um produto para venda na tabela produtoAnimais
    public BigDecimal consultarPrecoProduto(String produtoNome) throws SQLException {
        String sql = "SELECT preco_unitario FROM produtoAnimais WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produtoNome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("preco_unitario");
                }
            }
        }
        return null;
    }

}
