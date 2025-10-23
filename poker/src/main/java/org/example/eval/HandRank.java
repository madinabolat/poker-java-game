package org.example.eval;

public enum HandRank {
    ROYAL_FLUSH, // A-K-Q-J-10, all the same suit
    STRAIGHT_FLUSH, // five cards in a sequence, all the same suit. Ex.: 7-6-5-4-3, all hearts
    FOUR_OF_A_KIND, //four cards of the same rank. Ex.: 9-9-9-9 + card.
    FULL_HOUSE, //three of a kind + a pair. Ex.: 5-5-5 + 8-8.
    FLUSH, //Five cards of the same suit, not in sequence. Ex.: A-J-9-5-2 all hearts.
    STRAIGHT, //five cards in a sequence, any suits. 10-9-8-7-6. Ace can be high or low.
    THREE_OF_A_KIND, //three cards of the same rank. Ex.: 5-5-5 + two cards.
    TWO_PAIR, //two pairs of diff ranks. Ex.: J-J + 4-4 + card.
    ONE_PAIR,//two cards of the same rank. Ex.: Q-Q + three cards.
    HIGH_CARD
}

