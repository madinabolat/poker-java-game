package org.example;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.deck.Rank;
import org.example.game.Dealer;

import java.util.ArrayList;
import java.util.Comparator;
//import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.display();
        deck.shuffle();
        System.out.println("After shuffling");
        deck.display();

        Dealer pokerHand = new Dealer();
        pokerHand.dealHands();
        pokerHand.displayCommunityCards();

        pokerHand.getCommunityCards().sort(Comparator.comparing(Card::getRank));



        System.out.println("After sorting");
        pokerHand.displayCommunityCards();


    }
}