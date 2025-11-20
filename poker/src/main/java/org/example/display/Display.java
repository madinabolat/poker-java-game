package org.example.display;

import org.example.deck.Card;
import java.util.ArrayList;

public class Display {

    public void displaySectionHeader(String text){
        System.out.println(text);
    }



    public void displayMessageAndCards(String round, ArrayList<Card> cards) {
        System.out.println(round);
        for (Card card : cards) {
            System.out.print("[" + card.rank + ", " + card.suit + "] ");
        }
        System.out.println("\n");
    }
}
