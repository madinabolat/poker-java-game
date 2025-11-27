package org.example.eval;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandEvaluatorTest {
    HandEvaluator handEvaluator;
    ArrayList<Card> handCombination;

    @BeforeEach
    void setup(){
        handEvaluator = new HandEvaluator();
        handCombination = new ArrayList<>();
    }

    @Test
    public void detectsRegularStraight(){
        handCombination.add(new Card(Rank.SIX, Suit.HEARTS));
        handCombination.add(new Card(Rank.SEVEN, Suit.SPADES));
        handCombination.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.NINE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.TEN, Suit.DIAMONDS));

        assertTrue(handEvaluator.isStraight(handCombination));
    }

    @Test
    public void detectsStraightWithAceLow(){
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.SPADES));
        handCombination.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.FIVE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.ACE, Suit.DIAMONDS));

        assertTrue(handEvaluator.isStraight(handCombination));
    }

    @Test
    public void detectsStraightWithAceHigh(){
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        handCombination.add(new Card(Rank.KING, Suit.SPADES));
        handCombination.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.JACK, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.ACE, Suit.DIAMONDS));

        assertTrue(handEvaluator.isStraight(handCombination));
    }

    @Test
    public void detectsNonStraight(){
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        handCombination.add(new Card(Rank.KING, Suit.SPADES));
        handCombination.add(new Card(Rank.TWO, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.JACK, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.ACE, Suit.DIAMONDS));

        assertFalse(handEvaluator.isStraight(handCombination));
    }

    @Test
    public void detectsNonStraightWhenDuplicate(){
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.SPADES));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));  // Duplicate!
        handCombination.add(new Card(Rank.FOUR, Suit.CLUBS));
        handCombination.add(new Card(Rank.FIVE, Suit.HEARTS));

        assertFalse(handEvaluator.isStraight(handCombination));
    }

    @Test
    public void detectsNonStraightWhenGap(){
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.SPADES));
        handCombination.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.SIX, Suit.CLUBS));  // Gap!
        handCombination.add(new Card(Rank.SEVEN, Suit.HEARTS));

        assertFalse(handEvaluator.isStraight(handCombination));
    }
}