package org.example;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.example.eval.HandEvaluator;
import org.example.game.Dealer;

import java.util.ArrayList;
import java.util.Comparator;
//import org.example.game.Dealer;

public class PokerApp {
    public static void main(String[] args) {
//        Deck deck = new Deck();
//        deck.display();
//        deck.shuffle();
//        System.out.println("After shuffling");
//        deck.display();
//
//        Dealer pokerHand = new Dealer();
//        pokerHand.dealHands();
//        pokerHand.displayCommunityCards();
//
//        pokerHand.getCommunityCards().sort(Comparator.comparing(Card::getRank));

        ArrayList<Card> testDeck = new ArrayList<Card>();
        testDeck.add(new Card(Rank.TEN, Suit.DIAMONDS));
        testDeck.add(new Card(Rank.NINE, Suit.DIAMONDS));
        testDeck.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
        testDeck.add(new Card(Rank.SIX, Suit.DIAMONDS));
        testDeck.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
        HandEvaluator handEvaluator = new HandEvaluator();
        System.out.println(handEvaluator.determineRank(testDeck));



//        System.out.println("After sorting");
//        pokerHand.displayCommunityCards();
//
//        System.out.println(Rank.ACE.ordinal());
//        System.out.println(Rank.TEN.ordinal());

    }
}