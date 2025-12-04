package org.example.eval;

import org.example.deck.Card;
import org.example.deck.Rank;
import org.example.deck.Suit;

import java.lang.reflect.Array;
import java.util.*;

public class HandEvaluator {

    public HandResult determineBestHand(ArrayList<Card> communityCards, ArrayList<Card> playerHoleCards){
        ArrayList<Card> handCombination = new ArrayList<>();
        handCombination.addAll(communityCards);
        handCombination.addAll(playerHoleCards);
        ArrayList<ArrayList<Card>> allPossibleHandCombinations = generateKCardCombinations(handCombination,5);

        HandResult handResult = new HandResult(HandRank.HIGH_CARD, new ArrayList<>(), new ArrayList<>()); //handRank for HIGH_CARD is 9. the lower the better.

        for (ArrayList<Card> combination : allPossibleHandCombinations){
            HandResult currentHandResult = determineRank(combination);
            if (currentHandResult.isBetterThan(handResult)){
                handResult = currentHandResult;
            }
        }
        return handResult;
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

    public ArrayList<Integer> returnMainHandRankOrdinals(HashMap<Rank, Integer> rankCounts, int targetCountValue){
        ArrayList<Integer> mainHandOrdinals = new ArrayList<>();
        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()){
            if (entry.getValue().equals(targetCountValue)){
                mainHandOrdinals.add(entry.getKey().ordinal());
            }
        }
        return mainHandOrdinals;
    }

    public ArrayList<Integer> returnKickerHandRankOrdinals(HashMap<Rank, Integer> rankCounts, int mainHandCountValue){
        ArrayList<Integer> kickerHandOrdinals = new ArrayList<>();
        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()){
            if (!entry.getValue().equals(mainHandCountValue)){
                kickerHandOrdinals.add(entry.getKey().ordinal());
            }
        }
        Collections.sort(kickerHandOrdinals);
        return kickerHandOrdinals;
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

    public HandResult determineRank(ArrayList<Card> handCombination){
        HashSet<Suit> uniqueSuits = returnUniqueSuits(handCombination);
        HashMap<Rank, Integer> rankCounts = returnRanksWithCountOfEachRank(handCombination);
        int royalFlushCardsCount = countRoyalFlushCards(handCombination);
        int rankOrdinalsSum = sumRankOrdinals(handCombination);
        int pairsCount = countPairs(handCombination);

        sortByAscendingRank(handCombination);

        if (uniqueSuits.size() == 1){
            if (royalFlushCardsCount == 5){
                return new HandResult(HandRank.ROYAL_FLUSH, returnMainHandRankOrdinals(rankCounts,1), returnKickerHandRankOrdinals(rankCounts,1)); //TEN,JACK,QUEEN,KING,ACE of the same suit
            }
            if (rankOrdinalsSum % 5 == 0){
                return new HandResult(HandRank.STRAIGHT_FLUSH, returnMainHandRankOrdinals(rankCounts,1), returnKickerHandRankOrdinals(rankCounts,1)); //SEVEN,EIGHT,NINE,TEN,JACK (a sequence) of the same suit
            }
            return new HandResult(HandRank.FLUSH, returnMainHandRankOrdinals(rankCounts,1), returnKickerHandRankOrdinals(rankCounts,1)); //any five cards of the same suit
        }

        if (rankCounts.containsValue(4)){
            return new HandResult(HandRank.FOUR_OF_A_KIND, returnMainHandRankOrdinals(rankCounts,4), returnKickerHandRankOrdinals(rankCounts,4));
        }

        if (pairsCount == 1 && rankCounts.containsValue(3)){
            ArrayList<Integer> pairOrdinals = returnMainHandRankOrdinals(rankCounts,2);
            ArrayList<Integer> tripleOrdinals = returnMainHandRankOrdinals(rankCounts,3);
            return new HandResult(HandRank.FULL_HOUSE, tripleOrdinals, pairOrdinals); //THREE_OF_A_KIND and ONE_PAIR
        }

        if (isStraight(handCombination)){
            return new HandResult(HandRank.STRAIGHT, returnMainHandRankOrdinals(rankCounts,1), returnKickerHandRankOrdinals(rankCounts,1));
        }

        if (rankCounts.containsValue(3)){
            return new HandResult(HandRank.THREE_OF_A_KIND, returnMainHandRankOrdinals(rankCounts,3), returnKickerHandRankOrdinals(rankCounts,3));
        }

        if (pairsCount == 2){
            return new HandResult(HandRank.TWO_PAIR, returnMainHandRankOrdinals(rankCounts,2), returnKickerHandRankOrdinals(rankCounts,2));
        }

        if (pairsCount == 1){
            return new HandResult(HandRank.ONE_PAIR, returnMainHandRankOrdinals(rankCounts,2), returnKickerHandRankOrdinals(rankCounts,2));
        }

        return new HandResult(HandRank.HIGH_CARD, returnMainHandRankOrdinals(rankCounts,0), returnKickerHandRankOrdinals(rankCounts,0));
    }

}