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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void determinesWinnerOnePairBetterKicker(){
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




}
