package com.fazenda.dao;

import com.fazenda.model.Animal;
import com.fazenda.model.EstadoSaude;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends BaseDAO {

    public AnimalDAO() {
        super();
    }

    // Retorna o ID do registro de estoque ou o cria, se não existir
    public int getOrCreateEstoqueId(int tipoAnimalId, int ranchoId) throws SQLException {
        String sqlSelect = "SELECT id FROM estoque_animais WHERE fk_id_tipo_animal = ? AND fk_id_rancho = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlSelect)) {
            stmt.setInt(1, tipoAnimalId);
            stmt.setInt(2, ranchoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                String sqlInsert = "INSERT INTO estoque_animais (fk_id_tipo_animal, quantidade, fk_id_rancho) VALUES (?, 0, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setInt(1, tipoAnimalId);
                    insertStmt.setInt(2, ranchoId);
                    insertStmt.executeUpdate();
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Falha ao criar registro em estoque_animais.");
                    }
                }
            }
        }
    }

    // Método para criar um novo animal
    public void criarAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO animais (nome, sexo, peso, estado_saude, data_nascimento, fk_id_tipo_animal, fk_id_rancho) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, String.valueOf(animal.getSexo()));
            stmt.setFloat(3, animal.getPeso());
            // Armazena a descrição do estado, que deve corresponder ao ENUM da tabela
            stmt.setString(4, animal.getEstadoSaude().getDescricao());
            stmt.setDate(5, new Date(animal.getDataNascimento().getTime()));
            stmt.setInt(6, animal.getFkIdTipoAnimal());
            stmt.setInt(7, animal.getFkIdRancho());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }
        }
    }

    // Método para atualizar o estoque de animais (agregado)
    public void atualizarEstoque(int tipoAnimalId, int quantidade, int ranchoId) throws SQLException {
        String sql = "INSERT INTO estoque_animais (fk_id_tipo_animal, quantidade, fk_id_rancho) "
                   + "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantidade = quantidade + ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tipoAnimalId);
            stmt.setInt(2, quantidade);
            stmt.setInt(3, ranchoId);
            stmt.setInt(4, quantidade);
            stmt.executeUpdate();
        }
    }

    // Método para listar os animais do rancho
    public List<Animal> listarAnimais(int ranchoId) throws SQLException {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.id, a.nome, a.sexo, a.peso, a.estado_saude, a.data_nascimento, ta.nome AS tipo "
                   + "FROM animais a "
                   + "JOIN tiposAnimais ta ON a.fk_id_tipo_animal = ta.id "
                   + "WHERE a.fk_id_rancho = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ranchoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setSexo(rs.getString("sexo").charAt(0));
                    animal.setPeso(rs.getFloat("peso"));
                    // Converte a string do banco para o enum usando fromDescricao
                    animal.setEstadoSaude(EstadoSaude.fromDescricao(rs.getString("estado_saude")));
                    animal.setDataNascimento(rs.getDate("data_nascimento"));
                    animal.setTipoAnimal(rs.getString("tipo"));
                    animais.add(animal);
                }
            }
        }
        return animais;
    }
    
    // Método para alocar um animal em um local
    public void alocarAnimalLocal(int animalId, int localId) throws SQLException {
        String sql = "INSERT INTO animais_locais (fk_id_animais, fk_id_locais) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animalId);
            stmt.setInt(2, localId);
            stmt.executeUpdate();
        }
    }
    
    // Método para obter o tipo de alimentação do animal
    public String getTipoAlimentacao(int animalId) throws SQLException {
        String sql = "SELECT tipo_alimentacao FROM tiposAnimais ta "
                   + "JOIN animais a ON ta.id = a.fk_id_tipo_animal "
                   + "WHERE a.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animalId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("tipo_alimentacao") : null;
            }
        }
    }

    // Método para obter animais por tipo
    public List<Animal> getAnimaisPorTipo(int tipoAnimalId) throws SQLException {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM animais WHERE fk_id_tipo_animal = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tipoAnimalId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setSexo(rs.getString("sexo").charAt(0));
                    animal.setPeso(rs.getFloat("peso"));
                    animal.setEstadoSaude(EstadoSaude.fromDescricao(rs.getString("estado_saude")));
                    animal.setDataNascimento(rs.getDate("data_nascimento"));
                    animal.setFkIdTipoAnimal(tipoAnimalId);
                    animais.add(animal);
                }
            }
        }
        return animais;
    }
    
    // Método para atualizar um animal (CRUD)
    public void atualizarAnimal(Animal animal) throws SQLException {
        String sql = "UPDATE animais SET nome = ?, sexo = ?, peso = ?, estado_saude = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, String.valueOf(animal.getSexo()));
            stmt.setFloat(3, animal.getPeso());
            stmt.setString(4, animal.getEstadoSaude().getDescricao());
            stmt.setInt(5, animal.getId());
            stmt.executeUpdate();
        }
    }
    
    // Método para excluir um animal (CRUD)
    public void excluirAnimal(int animalId) throws SQLException {
        String sql = "DELETE FROM animais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animalId);
            stmt.executeUpdate();
        }
    }
}
