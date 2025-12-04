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


    @Test
    public void detectsRoyalFlush(){
        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
        handCombination.add(new Card(Rank.KING, Suit.HEARTS));
        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        assertEquals(HandRank.ROYAL_FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void doesNotReturnRoyalFlushWhenDifferentSuits(){
        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
        handCombination.add(new Card(Rank.KING, Suit.HEARTS));
        handCombination.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        assertNotEquals(HandRank.ROYAL_FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsStraightFlush(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.HEARTS));
        handCombination.add(new Card(Rank.FIVE, Suit.HEARTS));
        handCombination.add(new Card(Rank.SIX, Suit.HEARTS));
        handCombination.add(new Card(Rank.SEVEN, Suit.HEARTS));
        assertEquals(HandRank.STRAIGHT_FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void doesNotReturnStraightFlushWhenDifferentSuits(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.FIVE, Suit.HEARTS));
        handCombination.add(new Card(Rank.SIX, Suit.HEARTS));
        handCombination.add(new Card(Rank.SEVEN, Suit.HEARTS));
        assertNotEquals(HandRank.STRAIGHT_FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void doesNotReturnStraightFlushWhenNotConsecutive(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.FIVE, Suit.HEARTS));
        handCombination.add(new Card(Rank.SIX, Suit.HEARTS));
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        assertNotEquals(HandRank.STRAIGHT_FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsFourOfAKind(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.THREE, Suit.SPADES));
        handCombination.add(new Card(Rank.THREE, Suit.CLUBS));
        handCombination.add(new Card(Rank.TEN, Suit.HEARTS));
        assertEquals(HandRank.FOUR_OF_A_KIND, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsThreeOfAKind(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.TEN, Suit.SPADES));
        handCombination.add(new Card(Rank.THREE, Suit.CLUBS));
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals(HandRank.THREE_OF_A_KIND, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void doesNotReturnThreeOfAKindWhenFullHouse(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.TWO, Suit.SPADES));
        handCombination.add(new Card(Rank.THREE, Suit.CLUBS));
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        assertNotEquals(HandRank.THREE_OF_A_KIND, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsFullHouse(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.TWO, Suit.SPADES));
        handCombination.add(new Card(Rank.THREE, Suit.CLUBS));
        handCombination.add(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals(HandRank.FULL_HOUSE, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsFlush(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.HEARTS));
        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
        assertEquals(HandRank.FLUSH, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsTwoPair(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.SPADES));
        handCombination.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
        assertEquals(HandRank.TWO_PAIR, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void detectsOnePair(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.THREE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.ACE, Suit.HEARTS));
        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
        assertEquals(HandRank.ONE_PAIR, handEvaluator.determineRank(handCombination).handRank);
    }

    @Test
    public void returnsHighCardWhenNothingMatches(){
        handCombination.add(new Card(Rank.THREE, Suit.HEARTS));
        handCombination.add(new Card(Rank.FOUR, Suit.HEARTS));
        handCombination.add(new Card(Rank.ACE, Suit.DIAMONDS));
        handCombination.add(new Card(Rank.JACK, Suit.HEARTS));
        handCombination.add(new Card(Rank.QUEEN, Suit.HEARTS));
        assertEquals(HandRank.HIGH_CARD, handEvaluator.determineRank(handCombination).handRank);
    }
}