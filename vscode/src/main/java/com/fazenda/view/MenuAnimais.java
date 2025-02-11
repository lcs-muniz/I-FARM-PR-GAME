package com.fazenda.view;

import com.fazenda.service.AnimalService;
import java.util.Scanner;

public class MenuAnimais {
    Scanner scanner = new Scanner(System.in);
    private final AnimalService animalService;

    public MenuAnimais(AnimalService animalService) {
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        System.out.println("\n=== MENU ANIMAIS ===");
        System.out.println("1. Listar seus Animais");
        System.out.println("2. Alocar Animais");
        System.out.println("3. Alimentar Animais");
        System.out.println("4. Vender Animais");
        System.out.println("5. Sair");
        System.out.println("0. Voltar ao Menu Principal");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcao) {
            //case 1 -> animalService.comprarAnimal(scanner);
            //case 2 -> animalService.listarAnimais();
            //case 3 -> animalService.atualizarAnimal(scanner);
            //case 4 -> animalService.excluirAnimal(scanner);
            case 5 -> scanner.close();
            case 0 -> {} // Retornar ao menu principal
            default -> System.out.println("Opção inválida!");
        }
    }
    

}
