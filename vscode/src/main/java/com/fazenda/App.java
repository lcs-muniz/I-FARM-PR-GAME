package com.fazenda;

import com.fazenda.service.FazendeiroService;
import com.fazenda.view.GameView;

public class App {

    public static void main(String[] args) {
        FazendeiroService fazendeiroService = new FazendeiroService();
        GameView gameView = new GameView(fazendeiroService);
        gameView.iniciarJogo();
    }
}
