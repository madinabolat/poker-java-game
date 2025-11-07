package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Dealer {
    Player playerOne;
    Player playerTwo;
    private Deck gameDeck = new Deck();
    private ArrayList<Card> communityCards;

    public Dealer(){
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
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
        for (int i = 0; i<count; i++){
            playerOne.getHoleCards().add(iterator.next());
            iterator.remove();
            playerTwo.getHoleCards().add(iterator.next());
            iterator.remove();
        }
        burnCard(iterator);
    }

    public void dealHands(){
        System.out.println("DEALER IS IN THE GAME.\n");
        gameDeck.shuffle();

        Iterator<Card> iterator = gameDeck.deck.iterator();

        System.out.println("======DEALING HOLE CARDS======");
        dealPlayerCards(iterator, 2);
        System.out.println("Player One: [XX] [XX]");
        System.out.println("Player Two: [XX] [XX]");
        System.out.println("\n");

        System.out.println("======DEALING COMMUNITY CARDS======");

        dealCommunityCards(iterator, 3);
        displayMessageAndCards("<FLOP>", communityCards);

        dealCommunityCards(iterator, 1);
        displayMessageAndCards("<TURN>", communityCards);

        dealCommunityCards(iterator, 1);
        displayMessageAndCards("<RIVER>", communityCards);

        System.out.println("\n");
    }

    public void displayMessageAndCards(String round, ArrayList<Card> cards) {
        System.out.println(round);
        for (Card card : cards) {
            System.out.print("[" + card.rank + ", " + card.suit + "] ");
        }
        System.out.println("\n");
    }

    public void determineWinner(){
        HandEvaluator handEvaluator = new HandEvaluator();
        int playerOneBestRank = handEvaluator.determineBestRank(communityCards, playerOne.getHoleCards());
        int playerTwoBestRank = handEvaluator.determineBestRank(communityCards, playerTwo.getHoleCards());

        System.out.println("============SHOWDOWN============");
        displayMessageAndCards("PLAYER ONE: ", playerOne.getHoleCards());
        displayMessageAndCards("PLAYER TWO: ", playerTwo.getHoleCards());

        HandRank[] handRanks = HandRank.values();
        System.out.println("PLAYER ONE HAND: " + handRanks[playerOneBestRank]);
        System.out.println("PLAYER TWO HAND: " + handRanks[playerTwoBestRank]);
        System.out.println("\n");

        System.out.println("============GAME RESULT============");

        if (playerOneBestRank < playerTwoBestRank){
            System.out.println("          PLAYER ONE WON!");
        } else if (playerOneBestRank > playerTwoBestRank){
            System.out.println("          PLAYER TWO WON!");
        } else {
            System.out.println("            IT'S A TIE!");
        }
    }
}
