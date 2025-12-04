package org.example.eval;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.example.game.GameEvaluator;
import org.example.game.GameOutcome;
import org.example.game.GameResult;
import org.example.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameEvaluatorTest {
    Player playerOne = new Player("Alice");
    Player playerTwo = new Player("Bob");
    ArrayList<Card> communityCards = new ArrayList<>();
    ArrayList<Card> handCombinationOne = new ArrayList<>();
    ArrayList<Card> handCombinationTwo = new ArrayList<>();
    GameEvaluator evaluator = new GameEvaluator();
    GameResult result;

    @BeforeEach
    void setup(){
    }

    @Test
    public void determinesWinnerBothOnePairWithPlayerOneBetterKicker(){
        handCombinationOne.add(new Card(Rank.KING, Suit.SPADES));
        handCombinationOne.add(new Card(Rank.ACE, Suit.HEARTS));
        playerOne.setHoleCards(handCombinationOne);

        handCombinationTwo.add(new Card(Rank.KING, Suit.CLUBS));
        handCombinationTwo.add(new Card(Rank.NINE, Suit.DIAMONDS));
        playerTwo.setHoleCards(handCombinationTwo);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Rank.FIVE, Suit.SPADES));
        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
        communityCards.add(new Card(Rank.TWO, Suit.DIAMONDS));
        communityCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
        communityCards.add(new Card(Rank.THREE, Suit.SPADES));

        result = evaluator.determineWinner(playerOne, playerTwo, communityCards);

        GameResult expectedResult = new GameResult(GameOutcome.WIN, playerOne);

        assertEquals(GameOutcome.WIN, result.gameOutcome);
        assertEquals(playerOne, result.player);
    }

    @Test
    public void determinesWinnerOnePairTwoPair(){
        handCombinationOne.add(new Card(Rank.KING, Suit.SPADES));
        handCombinationOne.add(new Card(Rank.ACE, Suit.HEARTS));
        playerOne.setHoleCards(handCombinationOne);

        handCombinationTwo.add(new Card(Rank.KING, Suit.CLUBS));
        handCombinationTwo.add(new Card(Rank.NINE, Suit.DIAMONDS));
        playerTwo.setHoleCards(handCombinationTwo);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Rank.FIVE, Suit.SPADES));
        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
        communityCards.add(new Card(Rank.TWO, Suit.DIAMONDS));
        communityCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
        communityCards.add(new Card(Rank.ACE, Suit.SPADES));

        result = evaluator.determineWinner(playerOne, playerTwo, communityCards);

        GameResult expectedResult = new GameResult(GameOutcome.WIN, playerOne);

        assertEquals(GameOutcome.WIN, result.gameOutcome);
        assertEquals(playerOne, result.player);
    }

    @Test
    public void determinesWinnerWhenBothFullHouse(){
        handCombinationOne.add(new Card(Rank.KING, Suit.SPADES));
        handCombinationOne.add(new Card(Rank.THREE, Suit.HEARTS));
        playerOne.setHoleCards(handCombinationOne);

        handCombinationTwo.add(new Card(Rank.ACE, Suit.CLUBS));
        handCombinationTwo.add(new Card(Rank.THREE, Suit.DIAMONDS));
        playerTwo.setHoleCards(handCombinationTwo);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Rank.ACE, Suit.SPADES));
        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
        communityCards.add(new Card(Rank.THREE, Suit.SPADES));
        communityCards.add(new Card(Rank.THREE, Suit.CLUBS));
        communityCards.add(new Card(Rank.TEN, Suit.SPADES));

        result = evaluator.determineWinner(playerOne, playerTwo, communityCards);

        GameResult expectedResult = new GameResult(GameOutcome.WIN, playerOne);

        assertEquals(GameOutcome.WIN, result.gameOutcome);
        assertEquals(playerTwo, result.player);
    }

    @Test
    public void determinesWinnerWhenBothStraight(){
        handCombinationOne.add(new Card(Rank.SIX, Suit.SPADES));
        handCombinationOne.add(new Card(Rank.SEVEN, Suit.HEARTS));
        playerOne.setHoleCards(handCombinationOne);

        handCombinationTwo.add(new Card(Rank.JACK, Suit.CLUBS));
        handCombinationTwo.add(new Card(Rank.ACE, Suit.DIAMONDS));
        playerTwo.setHoleCards(handCombinationTwo);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Rank.NINE, Suit.SPADES));
        communityCards.add(new Card(Rank.TEN, Suit.HEARTS));
        communityCards.add(new Card(Rank.QUEEN, Suit.SPADES));
        communityCards.add(new Card(Rank.KING, Suit.CLUBS));
        communityCards.add(new Card(Rank.THREE, Suit.SPADES));

        result = evaluator.determineWinner(playerOne, playerTwo, communityCards);

        GameResult expectedResult = new GameResult(GameOutcome.WIN, playerOne);

        assertEquals(GameOutcome.WIN, result.gameOutcome);
        assertEquals(playerTwo, result.player);
    }

    @Test
    public void determinesWinnerWhenOneRoyalFlush(){
        handCombinationOne.add(new Card(Rank.TEN, Suit.HEARTS));
        handCombinationOne.add(new Card(Rank.SEVEN, Suit.HEARTS));
        playerOne.setHoleCards(handCombinationOne);

        handCombinationTwo.add(new Card(Rank.JACK, Suit.CLUBS));
        handCombinationTwo.add(new Card(Rank.ACE, Suit.DIAMONDS));
        playerTwo.setHoleCards(handCombinationTwo);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card(Rank.ACE, Suit.HEARTS));
        communityCards.add(new Card(Rank.KING, Suit.HEARTS));
        communityCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        communityCards.add(new Card(Rank.JACK, Suit.HEARTS));
        communityCards.add(new Card(Rank.THREE, Suit.SPADES));

        result = evaluator.determineWinner(playerOne, playerTwo, communityCards);

        GameResult expectedResult = new GameResult(GameOutcome.WIN, playerOne);

        assertEquals(GameOutcome.WIN, result.gameOutcome);
        assertEquals(playerOne, result.player);
    }





}
