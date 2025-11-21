package org.example;

import org.example.display.Display;
import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
        Display display = new Display();
        display.displaySectionHeader("WELCOME_TO_TEXAS_HOLD'EM_ROUND");
        Dealer dealer = new Dealer();
        dealer.dealHands();
        dealer.determineWinner();
    }
}