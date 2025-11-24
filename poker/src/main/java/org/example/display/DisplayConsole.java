package org.example.display;

import org.example.deck.Card;
import org.example.eval.HandRank;
import org.example.game.GameOutcome;
import org.example.game.GameResult;
import org.example.player.Player;

import java.util.ArrayList;

public class DisplayConsole implements DisplayInterface {

    public void displaySectionHeader(String text){
        System.out.println(text);
        printNewLine();
    }

    public void displayPlayerAndRank(Player player, HandRank handRank){
        System.out.println(player.name + " rank: " + handRank);
        printNewLine();
    }

    public void printNewLine(){
        System.out.println("\n");
    }

    public void displayGameResults(String message){
        System.out.println(message);
    }

    public void displayCards(ArrayList<Card> cards, boolean hidden){
        if (hidden == false){
            for (Card card : cards) {
                System.out.print("[" + card.rank + ", " + card.suit + "] ");
            }
        } else{
            for (Card card : cards) {
                System.out.print("[XX]");
            }
        }
        printNewLine();
    }

    public void displayPlayerCards(Player player, boolean hidden) {
        System.out.println(player.name);
        displayCards(player.getHoleCards(), hidden);
        printNewLine();
    }

    public void displayMessageAndCards(String text, ArrayList<Card> cards, boolean hidden) {
        System.out.println(text);
        displayCards(cards, hidden);
        printNewLine();
    }

    public void displayGameResult(GameResult gameResult) {
        if (gameResult.gameOutcome == GameOutcome.TIE){
            System.out.println("It's a TIE");
        } else{
            Player winner = gameResult.player;
            System.out.println(winner.name + " won!");
        }
    }
}
