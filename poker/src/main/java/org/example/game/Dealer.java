package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Dealer {
    Player playerOne; //to-think: if i want to have more players, do I just add here? or is there a better way?
    Player playerTwo;
    private Deck gameDeck = new Deck();
    private ArrayList<Card> communityCards;

    public Dealer(){
        playerOne = new Player("player one"); //todo: initialize players properly (get names from scanner)
        playerTwo = new Player("player two");
        communityCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards;
    }

    public void dealCommunityCards(Iterator<Card> iterator, int count){
        for (int i = 0; i<count; i++){
            communityCards.add(iterator.next());
            iterator.remove();
        }
        burnCard(iterator);
    }

    public void burnCard(Iterator<Card> iterator){
        iterator.next();
        iterator.remove();
    }

    public void dealPlayerCards(Iterator<Card> iterator, int count){
        //todo: make it so that any number of players could be passed
        for (int i = 0; i<count; i++){
            playerOne.getHoleCards().add(iterator.next());
            iterator.remove();
            playerTwo.getHoleCards().add(iterator.next());
            iterator.remove();
        }
        burnCard(iterator);
    }

    public void dealHands(){
        gameDeck.shuffle();

        Iterator<Card> iterator = gameDeck.deck.iterator();

        dealPlayerCards(iterator, 2);

        dealCommunityCards(iterator, 3);

        dealCommunityCards(iterator, 1);

        dealCommunityCards(iterator, 1);

    }

    public void displayCommunityCards(){
        System.out.println("Community Cards");
        for (Card card : communityCards){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }

        System.out.println("Player one cards");

        for (Card card : playerOne.getHoleCards()){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }

        System.out.println("Player two cards");

        for (Card card : playerTwo.getHoleCards()){
            System.out.println("Card: " + card.rank + ", " + card.suit);
        }
    }
}
