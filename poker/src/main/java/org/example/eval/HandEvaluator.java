package org.example.eval;

import org.example.deck.Card;

public class HandEvaluator {

    public HandEvaluator(){

    }

    public Card[] determineBestFive(Card[] handCombination){
        if (handCombination.length != 7 || handCombination == null){
            System.out.println("not valid input"); //todo: add exception
        }
        //community (5 cards) + 2 hole cards
        //pick best 5
        //all possible combinations of 5 cards
        //determine each rank?
        //find best?

        return handCombination; //placeholder
    }

    public HandRank determineRank(Card[] handCombination){
        if (handCombination.length != 5 || handCombination == null){
            System.out.println("not valid input"); //todo: add exception
        }
        //need to add sorting to array in the method for picking best 5 (sort by rank)
        //handCombination = [(SEVEN, HEARTS), (ACE, SPADES), (THREE, HEARTS), (JACK, DIAMONDS), (TEN, SPADES)]
        //(rank, suit)

        //determine     ROYAL_FLUSH, // A-K-Q-J-10, all the same suit and these exact ranks.
        //brute force is just handCombination[0].suit == handCombination[1].suit == ...
        //                    handCombination[0].rank == HandRank.ACE, ... -> assuming the handCombination comes sorted by rank?

        return HandRank.HIGH_CARD;
    }

}

//best five
//give a rank
//compare ranks