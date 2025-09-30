package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PokerHand {
    Player playerOne;
    Player playerTwo;
    public Deck deck;
    public ArrayList<Card> gameDeck; //make private
    private ArrayList<Card> communityCards;

    public PokerHand(){
        playerOne = new Player("player one"); //todo: initialize players properly (get names from scanner)
        playerTwo = new Player("player two");
        deck = new Deck(); // should I initialize this way?
        gameDeck = new ArrayList<>();
        gameDeck = deck.deck; //is it right to create gameDeck this way?
        communityCards = new ArrayList<Card>();
    }

    public void dealHands(){


        Collections.shuffle(gameDeck);

        Iterator<Card> iterator = gameDeck.iterator();

        //add proper methods for all below to reuse code
        //deal player cards
        for (int i = 0; i<2; i++){
            playerOne.holeCards.add(iterator.next());
            iterator.remove();
            playerTwo.holeCards.add(iterator.next());
            iterator.remove();
        }

        //burn one card
        iterator.next();
        iterator.remove();

        //deal community cards
        for (int i = 0; i<3; i++){
            communityCards.add(iterator.next());
        }

        //burn one card
        iterator.next();
        iterator.remove();

        communityCards.add(iterator.next());
        iterator.remove();

        //burn one card
        iterator.next();
        iterator.remove();

        communityCards.add(iterator.next());
        iterator.remove();
    }

    public void displayCommCards(){
        System.out.println("Community Cards");
        for (Card card : communityCards){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }

        System.out.println("Player one cards");

        for (Card card : playerOne.holeCards){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }

        System.out.println("Player two cards");

        for (Card card : playerTwo.holeCards){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }
    }
}
