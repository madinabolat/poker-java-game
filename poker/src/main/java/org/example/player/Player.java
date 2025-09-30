package org.example.player;

import org.example.deck.Card;

import java.util.ArrayList;

public class Player {
    String name;
    private ArrayList<Card> holeCards;

    public Player (String name){
        this.name = name;
    }
}
