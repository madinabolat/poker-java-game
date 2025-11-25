package org.example;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.display.DisplayGUI;
import org.example.eval.HandEvaluator;
import org.example.game.Game;

import java.util.ArrayList;

public class PokerApp {
    public static void main(String[] args) {
        //DisplayInterface display = new DisplayConsole();
//        DisplayInterface display = new DisplayGUI(600,600);
//        Game game = new Game(display);
//        game.playRound();


        //(FOUR, DIAMONDS), (ACE, SPADES), (KING, CLUBS), (FOUR, CLUBS), (FIVE, CLUBS)

        ArrayList<Card> handCombination = new ArrayList<Card>();

        Card card1 = new Card(Rank.TEN, Suit.HEARTS);
        handCombination.add(card1);
        Card card2 = new Card(Rank.QUEEN, Suit.SPADES);
        handCombination.add(card2);
        Card card = new Card(Rank.FOUR, Suit.DIAMONDS);
        handCombination.add(card);

        for (Card c : handCombination){
            System.out.println("[" + c.rank + ", " + c.suit + "]");
        }


        HandEvaluator handEvaluator = new HandEvaluator();
        handEvaluator.sortByAscendingRank(handCombination);

        System.out.println("AFTER SORTING");

        for (Card c : handCombination){
            System.out.println("[" + c.rank + ", " + c.suit + "]");
        }







    }
}