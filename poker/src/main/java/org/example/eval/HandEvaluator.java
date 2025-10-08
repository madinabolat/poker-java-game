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
        //add logic on uniqueSuits implications. if only one unique rank -> certain hands will apply
        //if not go on with rank
        //sort based on rank
        handCombination.sort(Comparator.comparing(Card::getRank));
        //check hand combinations based on rank alone
        //if not go on with rank + suit? or there are diff combinations based on suit unique set or ranks

        return HandRank.HIGH_CARD;
    }

}