package com.fazenda.service;

import com.fazenda.dao.AnimalDAO;
import com.fazenda.dao.FazendeiroDAO;
import com.fazenda.dao.LojaDAO;
import com.fazenda.model.GameSession;
import com.fazenda.util.DatabaseConnection;
import java.sql.Connection;

public class AnimalService {
    private final AnimalDAO animalDAO;
    private final FazendeiroDAO fazendeiroDAO;
    private final Connection connection = DatabaseConnection.getConnection();
    private final GameSession session;
    private final LojaDAO lojaDAO;
    
    public AnimalService(GameSession session, LojaDAO lojaDAO) {
        this.animalDAO = new AnimalDAO();
        this.fazendeiroDAO = new FazendeiroDAO();
        this.session = session;
        this.lojaDAO = lojaDAO;
    }
    
    // métodos do AnimalService ainda não implementados
    // métodos do AnimalService ainda não implementados
    // métodos do AnimalService ainda não implementados
    
}
