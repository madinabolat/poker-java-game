package org.example.eval;

import java.util.ArrayList;
import java.util.Collections;

public class HandResult {
    public HandRank handRank;
    public ArrayList<Integer> mainHandOrdinals;
    public ArrayList<Integer> kickerCardsOrdinals;

    HandResult(HandRank handRank, ArrayList<Integer> mainHandOrdinals, ArrayList<Integer> kickerCardsOrdinals){
        this.handRank = handRank;
        this.mainHandOrdinals = new ArrayList<>(mainHandOrdinals);
        this.kickerCardsOrdinals = new ArrayList<>(kickerCardsOrdinals);
    }

    public boolean isBetterThan(HandResult anotherHand){
        if (this.handRank.ordinal() < anotherHand.handRank.ordinal()){
            return true; //the lower the handRank ordinal the better
        } else if (this.handRank.ordinal() == anotherHand.handRank.ordinal()){
            if (isHandOneOrdinalsBetterThanHandTwo(mainHandOrdinals, anotherHand.mainHandOrdinals)==1){
                return true;
            } else if (isHandOneOrdinalsBetterThanHandTwo(mainHandOrdinals, anotherHand.mainHandOrdinals)==-1){
                return false;
            } else {
                if (isHandOneOrdinalsBetterThanHandTwo(kickerCardsOrdinals, anotherHand.kickerCardsOrdinals)==1){
                    return true;
                } else if (isHandOneOrdinalsBetterThanHandTwo(kickerCardsOrdinals, anotherHand.kickerCardsOrdinals)==-1){
                    return false;
                }
                else{
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public int isHandOneOrdinalsBetterThanHandTwo(ArrayList<Integer> handOneOrdinals, ArrayList<Integer> handTwoOrdinals){
        //returns 1 if hand one is better, -1 if hand one is worse, 0 if they are the same
        if (handOneOrdinals.isEmpty() && handTwoOrdinals.isEmpty()){return 0;}
        if (handOneOrdinals.isEmpty()){return -1;}
        if (handTwoOrdinals.isEmpty()){return 1;}

        Collections.sort(handOneOrdinals, Collections.reverseOrder());
        Collections.sort(handTwoOrdinals, Collections.reverseOrder());
        for (int i = 0; i<handOneOrdinals.size();i++){
            if (handOneOrdinals.get(i)>handTwoOrdinals.get(i)){
                return 1;
            }
            else if (handOneOrdinals.get(i)<handTwoOrdinals.get(i)){
                return -1;
            }
            else {
                continue;
            }
        }
        return 0;
    }
}
