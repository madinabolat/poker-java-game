package org.example;

import Deck.Card;
import Deck.Rank;
import Deck.Suit;

public class Main {
    public static void main(String[] args) {
        Card card1 = new Card(Rank.TWO, Suit.CLUBS);
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
//        System.out.println(card1);
//        System.out.println(card2);
//        System.out.println(card1==card2);
//        System.out.println(card1==card1);
//        System.out.println(card1.rank.equals(card2.rank));
//        System.out.println(card1.rank.equals(card2.rank));
        System.out.println(card1.equals(card2));
        System.out.println(card1==card2);


    }
}