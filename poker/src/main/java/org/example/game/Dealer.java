package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.display.Display;
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
    Display display = new Display();

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
        display.displaySectionHeader("DEALER_IS_IN_THE_GAME");
        gameDeck.shuffle();

        Iterator<Card> iterator = gameDeck.deck.iterator();

        display.displaySectionHeader("DEALING_HOLE_CARDS");
        dealPlayerCards(iterator, 2);
        display.displayMessageAndCards("PLAYER_ONE", playerOne.getHoleCards(), false);
        display.displayMessageAndCards("PLAYER_TWO", playerTwo.getHoleCards(), false);

        display.displaySectionHeader("DEALING_COMMUNITY_CARDS");

        dealCommunityCards(iterator, 3);
        display.displayMessageAndCards("FLOP", communityCards, true);

        dealCommunityCards(iterator, 1);
        display.displayMessageAndCards("TURN", communityCards, true);

        dealCommunityCards(iterator, 1);
        display.displayMessageAndCards("RIVER", communityCards, true);
    }

    public void determineWinner(){
        HandEvaluator handEvaluator = new HandEvaluator();
        int playerOneBestRank = handEvaluator.determineBestRank(communityCards, playerOne.getHoleCards());
        int playerTwoBestRank = handEvaluator.determineBestRank(communityCards, playerTwo.getHoleCards());

        display.displaySectionHeader("SHOWDOWN");
        display.displayMessageAndCards("PLAYER_ONE: ", playerOne.getHoleCards(), true);
        display.displayMessageAndCards("PLAYER_TWO: ", playerTwo.getHoleCards(), true);

        HandRank[] handRanks = HandRank.values();
        display.displayPlayerAndRank(playerOne, handRanks[playerOneBestRank]);
        display.displayPlayerAndRank(playerTwo, handRanks[playerTwoBestRank]);

        display.displaySectionHeader("GAME_RESULT");

        if (playerOneBestRank < playerTwoBestRank){
            display.displayGameResults("PLAYER ONE WON!");
        } else if (playerOneBestRank > playerTwoBestRank){
            display.displayGameResults("PLAYER ONE WON!");
        } else {
            display.displayGameResults("IT'S A TIE");
        }
    }
}
