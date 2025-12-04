package org.example;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.example.display.DisplayConsole;
import org.example.display.DisplayInterface;
import org.example.display.DisplayGUI;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.eval.HandResult;
import org.example.game.Game;
import org.example.game.GameEvaluator;
import org.example.game.GameResult;
import org.example.player.Player;

import java.util.ArrayList;

public class PokerApp {
    public static void main(String[] args) {
        DisplayInterface display = new DisplayConsole();
//        DisplayInterface display = new DisplayGUI(600,600);
        Game game = new Game(display);
        game.playRound();


        //(FOUR, DIAMONDS), (ACE, SPADES), (KING, CLUBS), (FOUR, CLUBS), (FIVE, CLUBS)
//
//        ArrayList<Card> handCombination = new ArrayList<Card>();
//
//        Card card1 = new Card(Rank.TEN, Suit.HEARTS);
//        handCombination.add(card1);
//        Card card2 = new Card(Rank.QUEEN, Suit.SPADES);
//        handCombination.add(card2);
//        Card card = new Card(Rank.FOUR, Suit.DIAMONDS);
//        handCombination.add(card);
//
//        for (Card c : handCombination){
//            System.out.println("[" + c.rank + ", " + c.suit + "]");
//        }
//
//
//        HandEvaluator handEvaluator = new HandEvaluator();
//        handEvaluator.sortByAscendingRank(handCombination);
//
//        System.out.println("AFTER SORTING");
//
//        for (Card c : handCombination){
//            System.out.println("[" + c.rank + ", " + c.suit + "]");
//        }
//
//

//        ArrayList<Card> handCombination = new ArrayList<>();
//        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
//        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
//        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
//        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
//        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
//
//        HandEvaluator handEvaluator = new HandEvaluator();
//        HandResult handResult = handEvaluator.determineRank(handCombination);
//        System.out.println(handResult.handRank);
//        System.out.println(handResult.mainHandOrdinals);
//        System.out.println(handResult.kickerCardsOrdinals);



//        ArrayList<Card> communityCards = new ArrayList<>();
//        communityCards.add(new Card(Rank.ACE, Suit.SPADES));
//        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
//        communityCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
//        communityCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
//        communityCards.add(new Card(Rank.TWO, Suit.SPADES));
//
//        ArrayList<Card> playerCards = new ArrayList<>();
//        playerCards.add(new Card(Rank.KING, Suit.SPADES));
//        playerCards.add(new Card(Rank.NINE, Suit.HEARTS));
//
//
//        HandEvaluator handEvaluator = new HandEvaluator();
//        HandResult handResult = handEvaluator.determineBestHand(communityCards, playerCards);
//
//        System.out.println(handResult.handRank);
//        System.out.println(handResult.mainHandOrdinals);
//        System.out.println(handResult.kickerCardsOrdinals);

//        Player playerOne = new Player("Alice");
//        ArrayList<Card> handCombination = new ArrayList<>();
//        handCombination.add(new Card(Rank.KING, Suit.SPADES));
//        handCombination.add(new Card(Rank.NINE, Suit.HEARTS));
//        playerOne.setHoleCards(handCombination);
//
//        Player playerTwo = new Player("Bob");
//        ArrayList<Card> handCombination2 = new ArrayList<>();
//        handCombination2.add(new Card(Rank.QUEEN, Suit.CLUBS));
//        handCombination2.add(new Card(Rank.TEN, Suit.DIAMONDS));
//        playerTwo.setHoleCards(handCombination2);
//
//        ArrayList<Card> communityCards = new ArrayList<>();
//        communityCards.add(new Card(Rank.ACE, Suit.SPADES));
//        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
//        communityCards.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
//        communityCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
//        communityCards.add(new Card(Rank.THREE, Suit.SPADES));
//
//        GameEvaluator evaluator = new GameEvaluator();
//        GameResult result = evaluator.determineWinner(playerOne, playerTwo, communityCards);
//
//        System.out.println(result.player.name);
//        System.out.println(result.gameOutcome);




//
//        Player playerOne = new Player("Alice");
//        ArrayList<Card> handCombination = new ArrayList<>();
//        handCombination.add(new Card(Rank.KING, Suit.SPADES));
//        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
//        playerOne.setHoleCards(handCombination);
//
//        Player playerTwo = new Player("Bob");
//        ArrayList<Card> handCombination2 = new ArrayList<>();
//        handCombination2.add(new Card(Rank.KING, Suit.CLUBS));
//        handCombination2.add(new Card(Rank.NINE, Suit.DIAMONDS));
//        playerTwo.setHoleCards(handCombination2);
//
//        ArrayList<Card> communityCards = new ArrayList<>();
//        communityCards.add(new Card(Rank.FIVE, Suit.SPADES));
//        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
//        communityCards.add(new Card(Rank.TWO, Suit.DIAMONDS));
//        communityCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
//        communityCards.add(new Card(Rank.THREE, Suit.SPADES));
//
//        GameEvaluator evaluator = new GameEvaluator();
//        GameResult result = evaluator.determineWinner(playerOne, playerTwo, communityCards);
//
//        System.out.println(result.player.name);
//        System.out.println(result.gameOutcome);

    }
}