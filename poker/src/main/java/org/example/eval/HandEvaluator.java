package org.example.eval;

import org.example.deck.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

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

    public HandRank determineRank(ArrayList<Card> handCombination){
        HashSet<Card> uniqueSuits = new HashSet<Card>();

        if (handCombination.size() != 5 || handCombination == null){
            System.out.println("not valid input"); //todo: add exception
        }

        for (Card card : handCombination){
            uniqueSuits.add(card);
        }

        if (uniqueSuits.size() == 1){
            return HandRank.FLUSH;
        }

        //if in a sequence -> STRAIGHT_FLUSH

//        ROYAL_FLUSH, // A-K-Q-J-10, all the same suit
//                FOUR_OF_A_KIND, //four cards of the same rank. Ex.: 9-9-9-9 + card.
//                FULL_HOUSE, //three of a kind + a pair. Ex.: 5-5-5 + 8-8.
//                STRAIGHT, //five cards in a sequence, any suits. 10-9-8-7-6. Ace can be high or low.
//                THREE_OF_A_KIND, //three cards of the same rank. Ex.: 5-5-5 + two cards.
//                TWO_PAIR, //two pairs of diff ranks. Ex.: J-J + 4-4 + card.
//                ONE_PAIR,//two cards of the same rank. Ex.: Q-Q + three cards.
//                HIGH_CARD



        //add logic on uniqueSuits implications. if only one unique rank -> certain hands will apply
        //if not go on with rank
        //sort based on rank
        handCombination.sort(Comparator.comparing(Card::getRank));
        //check hand combinations based on rank alone
        //if not go on with rank + suit? or there are diff combinations based on suit unique set or ranks

        return HandRank.HIGH_CARD;
    }

}