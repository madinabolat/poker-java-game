package org.example.player;
import org.example.deck.Card;
import java.util.ArrayList;

public class Player {
    String name;
    private ArrayList<Card> holeCards = new ArrayList<Card>(); //this will ensure holeCards are automaticallu created when calling new Player()

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
