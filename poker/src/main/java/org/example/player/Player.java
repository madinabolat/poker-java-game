package org.example.player;
import org.example.deck.Card;
import java.util.ArrayList;

public class Player {
    String name;
    private ArrayList<Card> holeCards = new ArrayList<Card>();

    public Player (String name){
        this.name = name;
    }

    public ArrayList<Card> getHoleCards() {
        return holeCards;
    }

    public void setHoleCards(ArrayList<Card> holeCards) {
        this.holeCards = holeCards;
    }
}
