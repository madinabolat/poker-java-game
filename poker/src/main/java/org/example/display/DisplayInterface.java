package org.example.display;

import org.example.deck.Card;
import org.example.eval.HandRank;
import org.example.game.GameOutcome;
import org.example.game.GameResult;
import org.example.player.Player;

import java.util.ArrayList;

public interface DisplayInterface {

    public void displaySectionHeader(String text);

    public void displayPlayerAndRank(Player player, HandRank handRank);

    public void printNewLine();

    public void displayGameResults(String message);

    public void displayCards(ArrayList<Card> cards, boolean hidden);

    public void displayPlayerCards(Player player, boolean hidden);

    public void displayMessageAndCards(String text, ArrayList<Card> cards, boolean hidden);

    public void displayGameResult(GameResult gameResult);
}
