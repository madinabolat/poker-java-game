package org.example;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.display.DisplayGUI;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.eval.HandResult;
import org.example.game.Game;
import org.example.game.GameEvaluator;
import org.example.game.GameResult;
import org.example.player.Player;

import java.util.ArrayList;

public class PokerApp {
    public static void main(String[] args) {
        //DisplayInterface display = new DisplayConsole();
        DisplayInterface display = new DisplayGUI(600,600);
        Game game = new Game(display);
        game.playRound();
    }
}