package org.example.eval;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class HandEvaluator {

    public HandEvaluator(){

    }

    public ArrayList<Card> determineBestFive(ArrayList<Card> communityCards, ArrayList<Card> playerHoleCards){
        ArrayList<Card> handCombination = new ArrayList<>();
        handCombination.addAll(communityCards);
        handCombination.addAll(playerHoleCards);

        

        return null;
    }

    public ArrayList<Card> helperSubsets(ArrayList<Card> Cards, int n, int k){
        if (k==n) return helperSubsets(Cards, n, k);
        return helperSubsets(Cards, n, k-1)
    }



    public HandRank determineRank(ArrayList<Card> handCombination){
        HashSet<Suit> uniqueSuits = new HashSet<Suit>();
        HashMap<Rank, Integer> rankCounts = new HashMap<>();

        if (handCombination.size() != 5 || handCombination == null){
            System.out.println("not valid input"); //todo: add exception
        }

        //I didnt fully understand how this sort works - go through
        handCombination.sort(Comparator.comparing(Card::getRank));

        for (Card card : handCombination){
            uniqueSuits.add(card.suit);
            if (rankCounts.containsKey(card.rank)){
                rankCounts.put(card.rank, rankCounts.get(card.rank)+1);
            }
            else {
                rankCounts.put(card.rank, 1);
            }

        }

        int royal_count = 0;
        int rank_ordinals_sum = 0;
        for (Rank rank : rankCounts.keySet()){
            if (rank.ordinal()>8){
                royal_count += 1;
            }
            rank_ordinals_sum += 1;
        }

        if (uniqueSuits.size() == 1){
            if (royal_count == 5){
                return HandRank.ROYAL_FLUSH;
            }

            if (rank_ordinals_sum % 5 == 0){
                return HandRank.STRAIGHT_FLUSH;
            }

            return HandRank.FLUSH;
        }

        if (rankCounts.containsValue(4)){
            return HandRank.FOUR_OF_A_KIND;
        }

        int pair_counter = 0;
        if (rankCounts.containsValue(2)){
            pair_counter += 1;
        }

        if (pair_counter == 1 && rankCounts.containsValue(3)){
            return HandRank.FULL_HOUSE;
        }

        if (rank_ordinals_sum % 5 == 0){
            return HandRank.STRAIGHT;
        }

        if (rankCounts.containsValue(3)){
            return HandRank.THREE_OF_A_KIND;
        }

        if (pair_counter == 2){
            return HandRank.TWO_PAIR;
        }

        if (pair_counter == 1){
            return HandRank.ONE_PAIR;
        }

        return HandRank.HIGH_CARD;
    }

    public int determineRankOrdinal(HandRank handRank){
        return handRank.ordinal();
    }

}

//(FOUR, DIAMONDS), (ACE, SPADES), (KING, CLUBS), (FOUR, CLUBS), (FIVE, CLUBS)
//sorted: (FOUR, DIAMONDS), (FOUR, CLUBS), (FIVE, CLUBS), (KING, CLUBS), (ACE, SPADES)
//uniqueSuits = {DIAMONDS, CLUBS, SPADES}
//rankCounts = {(FOUR,2), (FIVE,1), (KING,1), (ACE,1)}

//royal flush
//(TEN, DIAMONDS), (KING, DIAMONDS), (JACK, DIAMONDS), (ACE, DIAMONDS), (QUEEN, DIAMONDS)
//sorted: (TEN, DIAMONDS),  (JACK, DIAMONDS), (QUEEN, DIAMONDS), (KING, DIAMONDS), (ACE, DIAMONDS)
//uniqueSuits = {DIAMONDS}
//rankCounts = {(TEN,1), (JACK,1), (QUEEN,1), (KING,1), (ACE,1)}

//straight flush
//(TEN, DIAMONDS), (NINE, DIAMONDS), (SEVEN, DIAMONDS), (SIX, DIAMONDS), (EIGHT, DIAMONDS)
//sorted: (SIX, DIAMONDS),  (SEVEN, DIAMONDS), (EIGHT, DIAMONDS), (NINE, DIAMONDS), (TEN, DIAMONDS)
//uniqueSuits = {DIAMONDS}
//rankCounts = {(SIX,1), (SEVEN,1), (EIGHT,1), (NINE,1), (TEN,1)}
//ordinals 4,5,6,7,8 -> 4,4+1,4+2,4+3,4+4 -> 4*5+(0+1+2+3+4+5) -> divisible by 5

//flush
//(FOUR, DIAMONDS), (ACE, DIAMONDS), (KING, DIAMONDS), (SIX, DIAMONDS), (FIVE, DIAMONDS)
//sorted: (FOUR, DIAMONDS), (FIVE, DIAMONDS), (SIX, DIAMONDS), (KING, DIAMONDS), (ACE, DIAMONDS)
//uniqueSuits = {DIAMONDS}
//rankCounts = {(FOUR,1), (FIVE,1), (SIX,1), (KING,1), (ACE,1)}
