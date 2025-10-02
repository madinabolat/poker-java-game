package org.example;

import org.example.deck.Deck;
import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.displayDeck();

        Dealer pokerHand = new Dealer();
        pokerHand.dealHands();
        pokerHand.displayCommCards();
    }
}