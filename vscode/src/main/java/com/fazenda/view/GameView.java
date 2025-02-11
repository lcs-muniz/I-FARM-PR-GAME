package com.fazenda.view;

import com.fazenda.dao.LojaDAO;
import com.fazenda.model.Fazendeiro;
import com.fazenda.model.GameSession;
import com.fazenda.model.Rancho;
import com.fazenda.service.AnimalService;
import com.fazenda.service.FazendeiroService;
import com.fazenda.service.GameService;
import com.fazenda.service.LojaService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class GameView {
    private final Scanner scanner = new Scanner(System.in);
    private final GameService gameService = new GameService();
    private final GameSession session = gameService.getSession();
    private final MenuPrincipal menuPrincipal;

    public GameView(FazendeiroService fazendeiroService) {
        AnimalService animalService = new AnimalService(session, new LojaDAO());
        LojaService lojaService = new LojaService(session);
        this.menuPrincipal = new MenuPrincipal(fazendeiroService, animalService, lojaService);
    }
    
    public void iniciarJogo() {
        try {
            System.out.println("Bem-vindo ao seu Rancho!");
            Fazendeiro fazendeiro = gameService.criarFazendeiro(scanner);
            Rancho rancho = gameService.criarRanchoInicial(fazendeiro);
            boolean executando = true;
            while(executando) {
                exibirCabecalho();
                System.out.println("1. Menu Principal");
                System.out.println("2. Gerenciar Fazenda");
                System.out.println("3. Sair");
                System.out.print("Escolha: ");
                String opcao = scanner.nextLine();
                switch(opcao) {
                    case "1":
                        menuPrincipal.exibir();
                        break;
                    case "2":
                        System.out.println("Função 'Gerenciar Fazenda' não implementada ainda.");
                        break;
                    case "3":
                        executando = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch(SQLException e) {
            System.err.println("Erro no jogo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private void exibirCabecalho() throws SQLException {
    System.out.println("\n====================================");
    System.out.printf("FAZENDEIRO ID: %d%n", session.getCurrentFarmerId());
    
    BigDecimal saldoAtual = new FazendeiroService().getSaldo(session.getCurrentFarmerId());
    System.out.printf("SALDO: %.2f%n", saldoAtual);
    
    System.out.println("====================================\n");
}
}