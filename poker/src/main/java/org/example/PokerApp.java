package org.example;

import org.example.deck.Deck;
//import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.display();
        deck.shuffle();
        System.out.println("After shuffling");
        deck.display();
//
//        Dealer pokerHand = new Dealer();
//        pokerHand.dealHands();
//        pokerHand.displayCommunityCards();
    }
}