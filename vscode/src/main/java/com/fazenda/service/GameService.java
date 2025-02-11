package com.fazenda.service;

import com.fazenda.dao.FazendeiroDAO;
import com.fazenda.dao.LocalDAO;
import com.fazenda.dao.RanchoDAO;
import com.fazenda.model.Fazendeiro;
import com.fazenda.model.GameSession;
import com.fazenda.model.Rancho;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class GameService {
    private final FazendeiroDAO fazendeiroDAO = new FazendeiroDAO();
    private final RanchoDAO ranchoDAO = new RanchoDAO();
    private final LocalDAO localDAO = new LocalDAO();
    private final GameSession session = new GameSession();
    
    public GameSession getSession() {
        return session;
    }
    
    public Fazendeiro criarFazendeiro(Scanner scanner) throws SQLException {
        System.out.println("Crie seu Fazendeiro:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        char sexo;
        while(true) {
            System.out.print("Sexo (M/F): ");
            String s = scanner.nextLine().trim().toUpperCase();
            if(s.equals("M") || s.equals("F")){
                sexo = s.charAt(0);
                break;
            } else {
                System.out.println("Digite M ou F.");
            }
        }
        int idade;
        while(true) {
            System.out.print("Idade: ");
            String input = scanner.nextLine().trim();
            try {
                idade = Integer.parseInt(input);
                if(idade > 0) break;
                else System.out.println("Idade deve ser positiva.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
        // Inicializa saldo  (1000.00)
        Fazendeiro fazendeiro = new Fazendeiro();
        fazendeiro.setNome(nome);
        fazendeiro.setSexo(sexo);
        fazendeiro.setIdade(idade);
        fazendeiro.setSaldo(new BigDecimal("1000.00"));
        fazendeiro.setNivel(1);
        int id = fazendeiroDAO.criarFazendeiro(fazendeiro);
        session.setCurrentFarmerId(id);
        return fazendeiro;
    }
    
    public Rancho criarRanchoInicial(Fazendeiro fazendeiro) throws SQLException {
        Rancho rancho = new Rancho();
        rancho.setNome(fazendeiro.getNome() + "'s Ranch");
        rancho.setNivel(1);
        rancho.setFkIdFazendeiro(fazendeiro.getId());
        int ranchoId = ranchoDAO.criarRancho(rancho);
        session.setCurrentRanchoId(ranchoId);
        localDAO.criarLocaisIniciais(ranchoId);
        return rancho;
    }
}
