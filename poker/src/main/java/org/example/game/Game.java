package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    public Dealer dealer = new Dealer();
    public GameEvaluator gameEvaluator = new GameEvaluator();
    private Deck gameDeck = new Deck();
    Player playerOne;
    Player playerTwo;
    private ArrayList<Card> communityCards;
    DisplayInterface display;

    public Game(DisplayInterface display){
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
        communityCards = new ArrayList<Card>();
        this.display = display;
    }

    public void dealCards(){
        display.displaySectionHeader("WELCOME TO TEXAS HOLD'EM ROUND");
        display.displaySectionHeader("DEALER IS IN THE GAME");
        gameDeck.shuffle();
        Iterator<Card> iterator = gameDeck.deck.iterator();

        display.displaySectionHeader("DEALING HOLE CARDS");
        dealer.dealPlayerCards(playerOne, playerTwo, iterator, 2);

        display.displayPlayerCards(playerOne, true);
        display.displayPlayerCards(playerTwo, true);

        display.displaySectionHeader("DEALING COMMUNITY CARDS");

        dealer.dealCommunityCards(communityCards, iterator, 3);
        display.displayMessageAndCards("FLOP", communityCards, false);

        dealer.dealCommunityCards(communityCards, iterator, 1);
        display.displayMessageAndCards("TURN", communityCards, false);

        dealer.dealCommunityCards(communityCards, iterator, 1);
        display.displayMessageAndCards("RIVER", communityCards, false);
    }

    public void announceResults(){
        display.displaySectionHeader("SHOWDOWN");
        display.displayPlayerCards(playerOne, false);
        display.displayPlayerCards(playerTwo, false);

        display.displaySectionHeader("PLAYER HANDS");
        HandRank[] handRanks = HandRank.values();
        display.displayPlayerAndRank(playerOne, gameEvaluator.determinePlayerBestHandRank(playerOne, communityCards));
        display.displayPlayerAndRank(playerTwo, gameEvaluator.determinePlayerBestHandRank(playerTwo, communityCards));


        display.displaySectionHeader("GAME RESULT");
        GameResult gameResult = gameEvaluator.determineWinner(playerOne, playerTwo, communityCards);
        display.displayGameResult(gameResult);
    }

    public void playRound(){
        dealCards();
        announceResults();
    }


}
