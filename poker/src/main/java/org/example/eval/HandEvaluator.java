package org.example.eval;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;

import java.lang.reflect.Array;
import java.util.*;

public class HandEvaluator {

    public int determineBestRank(ArrayList<Card> communityCards, ArrayList<Card> playerHoleCards){
        ArrayList<Card> handCombination = new ArrayList<>();
        handCombination.addAll(communityCards);
        handCombination.addAll(playerHoleCards);
        ArrayList<ArrayList<Card>> allPossibleHandCombinations = generateKCardCombinations(handCombination,5);

        int handRank = 9; //the lower the better

        for (ArrayList<Card> combination : allPossibleHandCombinations){
            handRank = Math.min(determineRankOrdinal(determineRank(combination)),handRank);
        }

        return handRank;
    }

    public ArrayList<ArrayList<Card>> generateKCardCombinations(ArrayList<Card> cards, int k){
        ArrayList<ArrayList<Card>> finalSubsets = new ArrayList<>();
        if (k == 0) return finalSubsets;
        if (k > cards.size()) return finalSubsets;
        if (k == 1){
            for (Card card : cards){
                ArrayList<Card> singletonSubset = new ArrayList<>();
                singletonSubset.add(card);
                finalSubsets.add(singletonSubset);
            }
            return finalSubsets;
        }
        ArrayList<ArrayList<Card>> smallerSubsets = generateKCardCombinations(cards,k-1);
        for (ArrayList<Card> subset : smallerSubsets){
            int lastCardIndex = cards.indexOf(subset.get(subset.size()-1));
            for (int i = lastCardIndex + 1; i < cards.size(); i++){
                Card card = cards.get(i);
                ArrayList<Card> newSubset= new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(card);
                finalSubsets.add(newSubset);
            }
        }
        return finalSubsets;
    }

    public HashSet<Suit> returnUniqueSuits(ArrayList<Card> handCombination){
        HashSet<Suit> uniqueSuits = new HashSet<Suit>();

        for (Card card : handCombination){
            uniqueSuits.add(card.suit);
        }

        return uniqueSuits;
    }

    public HashMap<Rank, Integer> returnRanksWithCountOfEachRank(ArrayList<Card> handCombination){
        HashMap<Rank, Integer> rankCounts = new HashMap<>();
        for (Card card : handCombination){
            if (rankCounts.containsKey(card.rank)){
                rankCounts.put(card.rank, rankCounts.get(card.rank)+1);
            }
            else {
                rankCounts.put(card.rank, 1);
            }
        }
        return rankCounts;
    }

    public ArrayList<Integer> returnRankOrdinals(ArrayList<Card> handCombination){
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        ArrayList<Integer> rankOrdinals = new ArrayList<Integer>();
        for (Rank rank : rankCounts.keySet()){
            rankOrdinals.add(rank.ordinal());
        }
        return rankOrdinals;
    }

    public void sortByAscendingRank(ArrayList<Card> handCombination){
        handCombination.sort(Comparator.comparing(Card::getRank));
    }

    public int countRoyalFlushCards(ArrayList<Card> handCombination){
        int royalFlushCardsCount = 0;
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        //rank.ordinal(Rank.TWO) == 0, ..., rank.ordinal(Rank.NINE) == 7,
        //starting from 8 - Royal Flush Cards (TEN, JACK, QUEEN, KING, ACE)
        for (Rank rank : rankCounts.keySet()){
            if (rank.ordinal()>=8){
                royalFlushCardsCount += 1;
            }
        }
        return royalFlushCardsCount;
    }

    public int sumRankOrdinals(ArrayList<Card> handCombination){
        int rankOrdinalsSum = 0;
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        for (Rank rank : rankCounts.keySet()){
            rankOrdinalsSum += rank.ordinal();
        }
        return rankOrdinalsSum;
    }

    public int countPairs(ArrayList<Card> handCombination){
        int pairsCount = 0;
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        for (int count : rankCounts.values()){
            if (count == 2){
                pairsCount += 1;
            }
        }
        return pairsCount;
    }

    public boolean isConsecutiveRanks(ArrayList<Integer> rankOrdinals, int startIndex, int endIndex){
        for (int i = startIndex; i < endIndex; i++){
            if (rankOrdinals.get(i+1)-rankOrdinals.get(i) != 1){
                return false;
            }
        }
        return true;
    }

    public boolean isStraight(ArrayList<Card> handCombination) {
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        if (rankCounts.size()<5){
            return false;
        }
        ArrayList<Integer> rankOrdinals = returnRankOrdinals(handCombination);
        Collections.sort(rankOrdinals);

        if (isConsecutiveRanks(rankOrdinals,0,4)){
            return true;
        } else if(rankOrdinals.get(4) == 12 && rankOrdinals.get(0) == 0 && isConsecutiveRanks(rankOrdinals,0,3)){
            //if the last card is Ace and the first is Two and if the first four cards are consecutives
            return true;
        } else{
            return false;
        }
    }

    public HandRank determineRank(ArrayList<Card> handCombination){
        HashSet<Suit> uniqueSuits = returnUniqueSuits(handCombination);
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        int royalFlushCardsCount = countRoyalFlushCards(handCombination);
        int rankOrdinalsSum = sumRankOrdinals(handCombination);
        int pairsCount = countPairs(handCombination);

        sortByAscendingRank(handCombination);

        if (uniqueSuits.size() == 1){
            if (royalFlushCardsCount == 5){
                return HandRank.ROYAL_FLUSH; //TEN,JACK,QUEEN,KING,ACE of the same suit
            }
            if (rankOrdinalsSum % 5 == 0){
                return HandRank.STRAIGHT_FLUSH; //SEVEN,EIGHT,NINE,TEN,JACK (a sequence) of the same suit
            }
            return HandRank.FLUSH; //any five cards of the same suit
        }

        if (rankCounts.containsValue(4)){
            return HandRank.FOUR_OF_A_KIND;
        }

        if (pairsCount == 1 && rankCounts.containsValue(3)){
            return HandRank.FULL_HOUSE; //THREE_OF_A_KIND and ONE_PAIR
        }

        if (isStraight(handCombination)){
            return HandRank.STRAIGHT;
        }

        if (rankCounts.containsValue(3)){
            return HandRank.THREE_OF_A_KIND;
        }

        if (pairsCount == 2){
            return HandRank.TWO_PAIR;
        }

        if (pairsCount == 1){
            return HandRank.ONE_PAIR;
        }

        return HandRank.HIGH_CARD;
    }

    public int determineRankOrdinal(HandRank handRank){
        return handRank.ordinal();
    }

    public void determineWinnerWhenSameRank(ArrayList<Card> playerOneHoleCards, ArrayList<Card> playerTwoHoleCards){

    }
}