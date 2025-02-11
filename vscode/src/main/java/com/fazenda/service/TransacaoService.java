package com.fazenda.service;

import com.fazenda.dao.TransacaoDAO;
import com.fazenda.model.Transacao;
import java.sql.SQLException;

public class TransacaoService {
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();
    
    public void registrarTransacao(Transacao transacao) throws SQLException {
        transacaoDAO.registrarTransacao(transacao);
    }
}
