package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.display.Display;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    public Dealer dealer = new Dealer();
    private Deck gameDeck = new Deck();
    Player playerOne;
    Player playerTwo;
    private ArrayList<Card> communityCards;
    Display display = new Display();

    public Game(){
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
        communityCards = new ArrayList<Card>();
    }

    public void dealCards(){
        display.displaySectionHeader("WELCOME_TO_TEXAS_HOLD'EM_ROUND");
        display.displaySectionHeader("DEALER_IS_IN_THE_GAME");
        gameDeck.shuffle();
        Iterator<Card> iterator = gameDeck.deck.iterator();

        display.displaySectionHeader("DEALING_HOLE_CARDS");
        dealer.dealPlayerCards(playerOne, playerTwo, iterator, 2);

        display.displayPlayerCards(playerOne, true);
        display.displayPlayerCards(playerTwo, true);

        display.displaySectionHeader("DEALING_COMMUNITY_CARDS");

        dealer.dealCommunityCards(communityCards, iterator, 3);
        display.displayMessageAndCards("FLOP", communityCards, false);

        dealer.dealCommunityCards(communityCards, iterator, 1);
        display.displayMessageAndCards("TURN", communityCards, false);

        dealer.dealCommunityCards(communityCards, iterator, 1);
        display.displayMessageAndCards("RIVER", communityCards, false);
    }

    public void announceResults(){
        display.displaySectionHeader("SHOWDOWN");
        display.displayMessageAndCards("PLAYER_ONE: ", playerOne.getHoleCards(), false);
        display.displayMessageAndCards("PLAYER_TWO: ", playerTwo.getHoleCards(), false);

        display.displaySectionHeader("PLAYER_HANDS");
        HandRank[] handRanks = HandRank.values();
        display.displayPlayerAndRank(playerOne, dealer.determinePlayerBestHandRank(playerOne, communityCards));
        display.displayPlayerAndRank(playerTwo, dealer.determinePlayerBestHandRank(playerTwo, communityCards));


        display.displaySectionHeader("GAME_RESULT");
        GameResult gameResult = dealer.determineWinner(playerOne, playerTwo, communityCards);
        display.displayGameResult(gameResult);
    }

    public void playRound(){
        dealCards();
        announceResults();
    }


}
