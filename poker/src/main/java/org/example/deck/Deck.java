package org.example.deck;

import java.util.ArrayList;

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



    //will remove later
    public void displayDeck(){
        for (Card card : deck){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }
    }

}
