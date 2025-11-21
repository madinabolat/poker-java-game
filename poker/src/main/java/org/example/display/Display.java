package org.example.display;

import org.example.deck.Card;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;

public class Display {

    public void displaySectionHeader(String text){
        System.out.println(text);
    }

    public void displayPlayerAndRank(Player player, HandRank handRank){
        System.out.println(player.name + " rank: " + handRank);
    }

    public void displayGameResults(String message){
        System.out.println(message);
    }



    public void displayMessageAndCards(String text, ArrayList<Card> cards, boolean hidden) {
        System.out.println(text);
        if (hidden == false){
            for (Card card : cards) {
                System.out.print("[" + card.rank + ", " + card.suit + "] ");
            }
        } else{
            for (Card card : cards) {
                System.out.print("[XX]");
            }
        }
    }
}
