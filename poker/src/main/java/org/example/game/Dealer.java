package org.example.game;

import org.example.deck.Card;
import org.example.deck.Deck;
import org.example.display.Display;
import org.example.eval.HandEvaluator;
import org.example.eval.HandRank;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Dealer {

    public void burnCard(Iterator<Card> iterator) {
        iterator.next();
        iterator.remove();
    }

    public void dealCommunityCards(ArrayList<Card> communityCards, Iterator<Card> iterator, int count) {
        for (int i = 0; i < count; i++) {
            communityCards.add(iterator.next());
            iterator.remove();
        }
        burnCard(iterator);
    }

    public void dealPlayerCards(Player playerOne, Player playerTwo, Iterator<Card> iterator, int count) {
        for (int i = 0; i < count; i++) {
            playerOne.getHoleCards().add(iterator.next());
            iterator.remove();
            playerTwo.getHoleCards().add(iterator.next());
            iterator.remove();
        }
        burnCard(iterator);
    }

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
