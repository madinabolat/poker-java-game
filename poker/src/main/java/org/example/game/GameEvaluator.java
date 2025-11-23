package org.example.game;

import org.example.deck.Card;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;

public class GameEvaluator {
    public int determinePlayerBestHandRankNumeric(Player player, ArrayList<Card> communityCards){
        HandEvaluator handEvaluator = new HandEvaluator();
        return handEvaluator.determineBestRank(communityCards, player.getHoleCards());
    }

    public HandRank determinePlayerBestHandRank(Player player, ArrayList<Card> communityCards){
        int handRank = determinePlayerBestHandRankNumeric(player, communityCards);
        HandRank[] handRanks = HandRank.values();
        return handRanks[handRank];
    }

    public GameResult determineWinner(Player playerOne, Player playerTwo, ArrayList<Card> communityCards) {
        int playerOneBestRank = determinePlayerBestHandRankNumeric(playerOne, communityCards);
        int playerTwoBestRank = determinePlayerBestHandRankNumeric(playerTwo, communityCards);

        if (playerOneBestRank == playerTwoBestRank) {
            return new GameResult(GameOutcome.TIE, null);
        } else {
            if (playerOneBestRank < playerTwoBestRank) {
                return new GameResult(GameOutcome.WIN, playerOne);
            } else {
                return new GameResult(GameOutcome.WIN, playerTwo);
            }
        }
    }
}
