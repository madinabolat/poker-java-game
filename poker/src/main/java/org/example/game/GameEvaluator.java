package org.example.game;

import org.example.deck.Card;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.eval.HandResult;
import org.example.player.Player;

import java.util.ArrayList;

public class GameEvaluator {
    public HandRank determinePlayerBestHandRank(Player player, ArrayList<Card> communityCards){

        HandEvaluator handEvaluator = new HandEvaluator();
        HandResult playerBestHand = handEvaluator.determineBestHand(communityCards, player.getHoleCards());
        return playerBestHand.handRank;
    }

    public GameResult determineWinner(Player playerOne, Player playerTwo, ArrayList<Card> communityCards) {
        HandEvaluator handEvaluator = new HandEvaluator();
        HandResult playerOneBestHand = handEvaluator.determineBestHand(communityCards, playerOne.getHoleCards());
        HandResult playerTwoBestHand = handEvaluator.determineBestHand(communityCards, playerTwo.getHoleCards());

        if (playerOneBestHand.isBetterThan(playerTwoBestHand)) {
            return new GameResult(GameOutcome.WIN, playerOne);
        } else if (playerTwoBestHand.isBetterThan(playerOneBestHand)) {
            return new GameResult(GameOutcome.WIN, playerTwo);
        } else {
            return new GameResult(GameOutcome.TIE, null);
        }
    }
}
