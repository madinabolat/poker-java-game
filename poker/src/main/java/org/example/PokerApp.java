package org.example;

import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
        System.out.println("WELCOME TO TEXAS HOLD'EM ROUND\n");
        Dealer dealer = new Dealer();
        dealer.dealHands();
        dealer.determineWinner();
    }
}