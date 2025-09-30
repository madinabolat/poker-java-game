package org.example.player;

import org.example.deck.Card;

import java.util.ArrayList;

public class Player {
    String name;
    public ArrayList<Card> holeCards; //make this private

    public Player (String name){
        this.name = name;
        holeCards = new ArrayList<Card>();
    }
}
