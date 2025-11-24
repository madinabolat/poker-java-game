package org.example;

import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.display.DisplayGUI;
import org.example.game.Game;

public class PokerApp {
    public static void main(String[] args) {
        DisplayGUI display = new DisplayGUI(600,300);
        display.setUpSwing();

//        DisplayInterface display = new DisplayConsole();
////        DisplayInterface display = new DisplayGUI();
//        Game game = new Game(display);
//        game.playRound();

    }
}