package org.example;

import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.display.DisplayGUI;
import org.example.game.Game;

public class PokerApp {
    public static void main(String[] args) {
        //DisplayInterface display = new DisplayConsole();
        DisplayInterface display = new DisplayGUI(600,600);
        Game game = new Game(display);
        game.playRound();

    }
}