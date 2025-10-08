package org.example.deck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public Suit[] suits;
    public Rank[] ranks;
    public ArrayList<Card> deck;

    public Deck(){
        suits = Suit.values();
        ranks = Rank.values();
        deck = new ArrayList<Card>();

        for (Suit suit : suits){
            for (Rank rank : ranks){
                Card card = new Card(rank, suit);
                deck.add(card);
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void display(){
        for (Card card : deck){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }
    }
}
