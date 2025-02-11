package com.fazenda.view;

import com.fazenda.service.LojaService;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuLoja {
    Scanner scanner = new Scanner(System.in);
    private final LojaService lojaService;

    public MenuLoja(LojaService lojaService) {
        this.scanner = new Scanner(System.in);
        this.lojaService = lojaService;
    }

    public void exibir() {
        System.out.println("\n=== MENU LOJA ===");
        System.out.println("1. Comprar Animais");
        System.out.println("2. Comprar Insumos");
        System.out.println("3. Vender Produtos");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Opção: ");
            
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        try {
            switch (opcao) {
                case 1 -> lojaService.comprarAnimais(scanner);
                case 2 -> lojaService.comprarInsumos(scanner);
                case 3 -> lojaService.venderProdutos(scanner);
                case 0 -> {} // Retornar ao menu principal
                default -> System.out.println("Opção inválida!");
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação: " + e.getMessage());
        }
    }
}

