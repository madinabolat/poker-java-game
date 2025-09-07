package Deck;

public class Card {
    public Rank rank;
    public Suit suit;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    //watch video on type casting: https://www.youtube.com/watch?v=bHWDG9bfDRg&list=PLAma_mKffTOSUkXp26rgdnC0PicnmnDak&index=31
    //video on equals override - make notes: https://www.youtube.com/watch?v=m7vFGL-N9eY

    @Override
    public boolean equals(Object obj) {
        //write edge cases
        Card card = (Card) obj;
        return this.rank == card.rank && this.suit == card.suit;
    }
}
