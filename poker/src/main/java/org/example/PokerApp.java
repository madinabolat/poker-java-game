package org.example;

import org.example.deck.Deck;
import org.example.game.PokerHand;

public class PokerApp {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.displayDeck();

        PokerHand pokerHand = new PokerHand();
        pokerHand.dealHands();
        pokerHand.displayCommCards();
    }
}