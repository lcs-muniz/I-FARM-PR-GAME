package com.fazenda.view;

import com.fazenda.service.AnimalService;
import com.fazenda.service.FazendeiroService;
import com.fazenda.service.LojaService;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final FazendeiroService fazendeiroService;
    private final AnimalService animalService;
    private final LojaService lojaService;
    
    public MenuPrincipal(FazendeiroService fazendeiroService, AnimalService animalService, LojaService lojaService) {
        this.scanner = new Scanner(System.in);
        this.fazendeiroService = fazendeiroService;
        this.animalService = animalService;
        this.lojaService = lojaService;
    }
    
    public void exibir() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Loja Compra/Venda"); // MenuLoja
            System.out.println("2. Gerenciar Animais"); // MenuAnimais
            //System.out.println("3. Alocar Animais"); // Não implementado ainda
            System.out.println("0. Sair");
            
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 -> new MenuLoja(lojaService).exibir();
                case 2 -> new MenuAnimais(animalService).exibir();
                //case 3 -> fazendeiroService.alocarAnimais(); // Não implementado ainda
                case 0 -> scanner.close();
                default-> System.out.println("Opção inválida!");
            }
        }
    }
}