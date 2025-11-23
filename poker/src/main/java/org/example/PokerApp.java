package org.example;

import org.example.display.Display;
import org.example.game.Dealer;
import org.example.game.Game;

public class PokerApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.playRound();
    }
}