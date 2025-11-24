package org.example.display;
import org.example.deck.Card;
import org.example.eval.HandRank;
import org.example.game.GameOutcome;
import org.example.game.GameResult;
import org.example.player.Player;

import javax.swing.*;
import java.util.ArrayList;

public class DisplayGUI implements DisplayInterface {
    public JFrame frame;
    public JButton welcomeButton;
    public JButton nextButton;
    public JTextArea textArea;
    public JPanel panel;
    private int width;
    private int height;

    public DisplayGUI(int width, int height){
        this.width = width;
        this.height = height;
        frame = new JFrame();
        welcomeButton = new JButton("Play!");
        textArea = new JTextArea(30,30);
        panel = new JPanel();

        panel.add(textArea);
        frame.setSize(width, height);
        frame.setTitle("WELCOME TO TEXAS HOLD'EM ROUND");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void printNewLine(){
        textArea.append("\n");
    }

    public void displaySectionHeader(String text){
        textArea.append(text);
        printNewLine();
        printNewLine();
    }

    public void displayPlayerAndRank(Player player, HandRank handRank){
        textArea.append(player.name + " rank: " + handRank);
        printNewLine();
        printNewLine();
    }

    public void displayGameResults(String message){
        textArea.append(message);
        printNewLine();
    }

    public void displayCards(ArrayList<Card> cards, boolean hidden){
        if (hidden == false){
            for (Card card : cards) {
                textArea.append("[" + card.rank + ", " + card.suit + "] ");
            }
        } else{
            for (Card card : cards) {
                textArea.append("[XX]");
            }
        }
        printNewLine();
    }

    public void displayPlayerCards(Player player, boolean hidden) {
        textArea.append(player.name + ": ");
        displayCards(player.getHoleCards(), hidden);
        printNewLine();
    }

    public void displayMessageAndCards(String text, ArrayList<Card> cards, boolean hidden) {
        textArea.append(text);
        displayCards(cards, hidden);
        printNewLine();
    }

    public void displayGameResult(GameResult gameResult) {
        if (gameResult.gameOutcome == GameOutcome.TIE){
            textArea.append("It's a TIE");
        } else{
            Player winner = gameResult.player;
            textArea.append(winner.name + " won!");
        }
    }
}

