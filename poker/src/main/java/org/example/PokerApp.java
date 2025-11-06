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

        Dealer dealer = new Dealer();
        dealer.dealHands();
        dealer.displayCommunityCards();
        dealer.determineWinner();



//        ArrayList<Card> testDeck = new ArrayList<Card>();
//        testDeck.add(new Card(Rank.TEN, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.NINE, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.SIX, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.ACE, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
//        testDeck.add(new Card(Rank.KING, Suit.DIAMONDS));
//        HandEvaluator handEvaluator = new HandEvaluator();
//        ArrayList<ArrayList<Card>> subsets = new ArrayList<>();
//        int k = 5;
//        subsets = handEvaluator.generateKCardCombinations(testDeck,k);
//        int counter = 1;
//        for (ArrayList<Card> subset : subsets){
//            System.out.println();
//            System.out.println("Subset " + counter + ": ");
//            for (Card card : subset){
//                System.out.print(card.rank + " " + card.suit + ", ");
//            }
//            counter ++;
//        }
    }
}