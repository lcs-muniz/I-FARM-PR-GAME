package com.fazenda.service;

import com.fazenda.dao.FazendeiroDAO;
import java.math.BigDecimal;
import java.sql.SQLException;

public class FazendeiroService {
    private final FazendeiroDAO fazendeiroDAO = new FazendeiroDAO();
    
    public void atualizarSaldoFazendeiro(int fazendeiroId, BigDecimal valor) throws SQLException {
        fazendeiroDAO.atualizarSaldo(fazendeiroId, valor);
    }
    
    public BigDecimal getSaldo(int fazendeiroId) throws SQLException {
        return fazendeiroDAO.getSaldo(fazendeiroId);
    }
}
